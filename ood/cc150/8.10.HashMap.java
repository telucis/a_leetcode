public class Hash<K, V> {
    private final int MAX_SIZE = 16;
    LinkedList<Cell<K, V>>[] items; 
    
    public Hash() {
        items = (LinkedList<Cell<K, V>>[]) new LinkedList[MAX_SIZE];
    }
    
    public int hashCodeOfKey(K key) {
        return key.toString().length() % items.length;
    }
    
    public void put(K key, V value) {
        int x = hashCodeOfKey(key);
        if (items[x] == null) {
            items[x] = new LinkedList<Cell<K, V>>();
        }
        LinkedList<Cell<K, V>> collided = items[x];
        for (Cell<K, V> c : collided) {
            if (c.equivalent(key)) {
                collided.remove(c);
                break;
            }
        }
        
        Cell<K, V> cell = new Cell<K, V>(key, value);
        collided.add(cell);
    }
    
    public V get(K key) {
        int x = hashCodeOfKey(key);
        if (items[x] == null) {
            return null;
        }
        LinkedList<Cell<K, V>> collided = items[x];
        for (Cell<K, V> c : collided) {
            if (c.equivalent(key)) {
                return c.getValue();
            }
        }
        
        return null;        
    }
    
    public void debugPrintHash() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(i + ": ");
            LinkedList<Cell<K, V>> list = items[i];
            if (list != null) {
                for (Cell<K, V> cell : list) {
                    System.out.print(cell.toString() + ", ");
                }
            }
            System.out.println("");
        }
    }
}

public class Cell<K, V> {
    private K key;
    private V value;
    public Cell(K k, V v) {
        key = k;
        value = v;
    }
    
    public boolean equivalent(Cell<K, V> c) {
        return equivalent(c.getKey());
    }
    
    public boolean equivalent(K k) {
        return key.equals(k);
    }
    
    @Override 
    public String toString() {
        return "(" + key.toString() + ", " + value.toString() + ")";
    }   
    
    public K getKey() {
        return key;
    }
    
    public V getValue() {
        return value;
    }
}

public class Dummy {
    private String name;
    private int age;
    public Dummy(String n, int a) {
        name = n;
        age = a;
    }
    
    @Override 
    public String toString() {
        return "(" + name + ", " + age + ")";
    }
    
    public int getAge() {
        return age;
    }
    
    public String getName() {
        return name;
    }
}

public class Test {

    public static void main(String[] args) {
        Dummy bob = new Dummy("Bob", 20);
        Dummy jim = new Dummy("Jim", 25);
        Dummy alex = new Dummy("Alex", 30);
        Dummy tim = new Dummy("Tim", 35);
        Dummy maxwell = new Dummy("Maxwell", 40);
        Dummy john = new Dummy("John", 45);
        Dummy julie = new Dummy("Julie", 50);
        Dummy christy = new Dummy("Christy", 55);
        Dummy tim2 = new Dummy("Tim", 100); // This should replace the first "tim"
        
        Dummy[] dummies = {bob, jim, alex, tim, maxwell, john, julie, christy, tim2};
        
        /* Test: Insert Elements. */
        Hash<String, Dummy> hash = new Hash<String, Dummy>();
        for (Dummy d : dummies) {
            hash.put(d.getName(), d);
        }
        
        hash.debugPrintHash();
        
        /* Test: Recall */
        for (Dummy d : dummies) {
            String name = d.getName();
            Dummy dummy = hash.get(name);
            System.out.println("Dummy named " + name + ": " + dummy.toString());
        }       
    }

}
