/**
 * Mini Cassandra
 * 
	 Cassandra is a NoSQL storage. The structure has two-level keys.

	Level 1: raw_key. The same as hash_key or shard_key.
	Level 2: column_key.
	Level 3: column_value

	raw_key is used to hash and can not support range query. let's simplify this to a string.
	column_key is sorted and support range query. let's simplify this to integer.
	column_value is a string. you can serialize any data into a string and store it in column value.

	implement the following methods:

	insert(raw_key, column_key, column_value)
	query(raw_key, column_start, column_end) // return a list of entries
	Have you met this question in a real interview?  Yes
	Example
	insert("google", 1, "haha")
	query("google", 0, 1)
	>> [（1, "haha")]
	方法：使用哈希影射和有序影射。
 */

/**
 * Definition of Column:
 * public class Column {
 *     public int key;
 *     public String value;
 *     public Column(int key, String value) {
 *         this.key = key;
 *         this.value = value;
 *    }
 * }
 */
public class MiniCassandra {
    private Map<String, TreeMap<Integer, String>> map = new HashMap<>();
 
    public MiniCassandra() {
        // initialize your data structure here.
    }
    
    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return void
     */
    public void insert(String raw_key, int column_key, String column_value) {
        // Write your code here
        TreeMap<Integer, String> tm = map.get(raw_key);
        if (tm == null) {
            tm = new TreeMap<>();
            map.put(raw_key, tm);
        }
        tm.put(column_key, column_value);
    }
 
    /**
     * @param raw_key a string
     * @param column_start an integer
     * @param column_end an integer
     * @return a list of Columns
     */
    public List<Column> query(String raw_key, int column_start, int column_end) {
        // Write your code here
        List<Column> results = new ArrayList<>();
        TreeMap<Integer, String> tm = map.get(raw_key);
        if (tm == null) return results;
        Map<Integer, String> queried = tm.subMap(column_start, true, column_end, true);
        for(int key : queried.keySet()) {
            results.add(new Column(key, queried.get(key)));
        }
        return results;
    }
}
