### Requirements

* Functional
    - Give a URL, generate a shorter and unique alias of it
    - Give a short URL, redirect to original link
    - pick custom alias
    - expire after a timespan or set a specify time
* non-Functional
    - highly available
    - redirect should in real-time with min latency
    - shorturl should not be guessable
* extended
    - how many times a redirection happened
    - REST APIs

### Capacity Estimation and Constraints

* read and write ratio : **100:1**

* Traffic estimates:
    - 500M new URLs per month
        + 500m / (30 * 24 * 3600) ~= **200URL/s**
    - 500M*100 = 50B redirect
        + 50b / (30 * 24 * 3600) ~= **19K/s**
* Storage estimates:
    - 500M new URLs per month and keep 5 years
        + 500m * 5 * 12 = **30billion**
    - each object be 500 bytes
        + 30b * 500byte = **15TB**
* Bandwidth estimates:
    - incoming data: 200 new URL per sec
        + 200 * 500bytes = **100KB/s**
    - outgoing data: 19K URL redirection per sec
        + 19k * 500bytes = **9MB/s**
* Memory estimates
    - cache some hot URL, follow 80-20 rule
    - 19k request per sec, and 1.7b request per day
        + 19k * 3600sec * 24hours ~= **1.7billion**
    - cache 20% of these URL
        + 0.2 * 1.7b * 500bytes ~= **170GB**

### System APIs

```
    createURL (
        api_dev_key, 
        original_url, 
        custom_alias=None, 
        user_name=None, 
        expire_data=None
    )
    deleteURL (
        api_dev_key,
        url_key
    )
```

### Dadabase Design

1. store billion of records
2. each object store is small
3. no relationships between records
4. read-heavy

Database Schema

1. URL - Nosql
    * Hash: varchar(16) - PK
    * FullUrl: text?
    * CreationDate: datetime
    * ExpirationData: datetime
    * UserID: int (if nosql: no use)
2. User - Sql
    * UserId: int - PK
    * Name: varchar(20)
    * Email: varchar(32)
    * CreationDate: datetime
    * LastLogin: datetime

### Basic System Design and Algorithm

generate a short and unique key for the given URL

#### a. Encoding actual URL
* compute a unique hash(MD5 or SHA256, etc) of given URL
    - produce a 128-bit hash code (16 byte)
* encoded for display(base64)
    - encode more than 20 characters (3->4)
    - take first 6 letters for the key
    - duplication: choose other characters or swap some characters
* length of the short key
    - 6 letter = 64^6 ~= 68.7 billion
    - 8 letter = 64^8 ~= 281 trillion

problem:

* multiple users enter same URL, get the same shortUrl
    - increasing sequence number (may be userId)
    - even after this if have a conflict, we have to generating a key until get a unique key

#### b. Generating keys offline

* have a standalone Key Generation Service (KGS) that generates random six letter
* beforhand and stores in a database (key-DB)

problem:

* concurrency problems: multiple servers reading keys concurrently, will read same key
    - KGS use two tables to store keys, noUsed/used
    - **KGS give keys to servers from noUsed table**
    - **KGS can keep some key in memory**
    - as soon as move these key, move them to used table
    - if server or KGS die, some key will be **wasting**
    - KGS has to not give the same key to multiple server, for that, it must synchronize the data structing holding the keys before removing keys from it and give them to a server
* what would be the key-DB size
    - with base64 encoding, will generate 68.7B unique 6 letter keys
    - if we need one byte to store one alpha-numeric character
        + 6(characters per key) & 68.7B(unique keys) => 412 GB
* Isn't KGS the single point of failure?
    - hava a standby replica of KGS
    - whenever primary server die, it take over to generate and provide key
* Can each app server cache some keys from key-DB
    - yes, this can surely speed things up
    - when the server dies, keys wiil be wasting, it could be acceptable
* How would we perform a key lookup
    - can loopup the key in our database or k-v store to get the full url
    - if present, then issue a "HTTP 302 Redirect"
    - if not present, issue a "HTTP 404 Not Found", redirect to homepage
* Should we impose size limits on custom aliased?
    - providing a custom alias is not mandatory
    - impose a size limit on a custom alias,to have a consistent URL database

### Data Partitioning and Replication

To scale out our DB, we need partition it so that it can store billion of URL

We need come up with a partitioning schema that divide and store data to different DB servers

#### a. Range Based Partitioning

#### b. Hash Based Partitioning
we take the hash of the 'key' or the actual URL to determine the partition to store the file.

### Cache

We can use off-the-shelf solutin like Memcache, that can store full URLs with their respective hashes.

problem:  
* How much cache should we have
    - start with 20% of daily traffic and based on clients's usage pattern we can adjust memory
    - as estimated above we need 170GB memory to cache 20% of daily traffic
    - we can fit all into one machine, or choose a couple of smaller servers to store all hot URLs
* Which cache eviction policy would best fit our needs
    - **Least Recently Used(LRU)** can be a reasonable policy
    - **Linked Hash Map** or a similar dataStructure also can keep track of which URLs are accessed recently
* How can each cache replica be updated?
    
### Load Balancer(LB)

we can add Load balancing layer at three places in our system:  
1. Between Client and Application servers
2. Between Application Servers and database servers
3. Between Application Servers and Cache servers

* Round Robin approach can be adopted
    - it's sample and does not introduce any overhead
    - if a server is dead, LB will take it out of the rotation
    - but it won't take server load into consideration

### Purging or DB cleanup

Should entries stick around forever or should they be purged?  
if a user-specified expiration time is reached, what should happend?

if we chose to actively search for expired links to remove them, it would put a lot of pressure on our database.  
we can slowly remove expired links and do a lazy cheanup.

* **Whenever a user tries to access an expired link, we can delete the link and return an error**
* A seperate Cleanup service can run periodically to remove links from storage and cache
* The service should be lightweight and scheduled to run only traffic is expected to be low
* We can have a default expiration time for each link, e.g., two years
* After removing an expired link, can put it's key back to key-DB

### Telemetry

problem:  
* How many times a short URL has been used
* what were user locations
* How would we store these statistices
* if it's part of a DB row that gets update on each view, what will happen when a popular URL is slammed with a large number of concurrent requests?

we should have statistics about the country of the visitor, date and time of access

### Security and Permissions

problem:  
* Can users create private URLs or allow a particular set of users to oaccess a URL?

* store permission level(public/private) with each URL
* also can create a table store UserIDs that have permission to see a specific URL
* store in a NoSQL wide-column database like Cassandra
    - key storing permissions would be the 'Hash'()
    - columns will store the UserIDs of those user have permission



