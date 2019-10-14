> Design a web crawler
> Design thread-safe producer and consumer
> Design a Typeahead

### Crawler

* Scenario
    - How may web Page? how long? how large?
    - crawl **1.6m web pages per second**
        + 1 trillion web page
        + crawl all of them every week
    - 10p (petabyte) web page storage
        + average size of a web page: 10k
* Service
    - Crawler
    - TaskService
    - StorageService
* Storage
    - Use db to store tasks
    - BigTable to store web pages

* A Simplistic News Crawler
    - give the URL of news list page
    - Send an HTTP request and grab the content of the news list page
    - Extract all the news titles from the news list page
* A Multi-threaded Web Crawler
    - more threads doesn't necessarily mean more performance
        + context switch cost (CPU number limitation)
        + thread (port) number limitation
        + network bottleneck for single machine
* A Distributed Web Crawler
    - How to design the task table
    ```sql
        CREATE TABLE TaskTable (
            `id`    bigint,
            `url`   text,
            `state` string,
            `priority`  int,
            `available_time`    timestamp
        )
    ```
* Scale
    - How to handle slow select
        + task table sharding
    - How to handle update for failure
        + Exponential back-off
    - How to handle dead cycle
        + detail: Too many web pages in sina.com, crawler keeps crawling sina.com and don't crawl other website
        + Use quota

### Typeahead

* Scenario - constrains
    - DAU: 500m
    - Search: 4*6*500 = 12b (every user searches 6 times, types 4 letters)
    - QPS: 12b/86400 = 138k
    - Peak QPS = QPS*2 = 276k
* Service
    - QueryService
    - DataCollectionService
* Storage
    - QueryService - Trie
        + in-memory trie along with disk serialization
        + Store the top n hot key words, search becomes O(len)
        + How to add a new record ({abd: 3b}) to trie
    - DataCollectionService
        + big table
        + come from log data
        ```sql
            CREATE TABLE DataCollection (
                `keyword`   string,
                `hit_count` bigint
            )
            CREATE TABLE Log (
                `user`      string,
                `keyword`   string,
                `timestamp` timestamp
            )
        ```
* Scale
    - How to reduce reponse time (front-end)
        + cache result
        + pre-fetch
    - What if the trie gets too large for one machine
        + stored across multiple machines
        + use consistent hashing to decide which machine a particular string belongs to
    - How to reduce the size of log file
        + Why do we need to reduce the size of it
            * we have to go through 20b log entries to get "amazon -> 20b"
            * storing 20b log costs a log memory
        + How to reduce the size of log
            * Probabilistic logging
            * Log with 1/1000 probability, i.e. every time we're about to log one hit of "Amazon"




