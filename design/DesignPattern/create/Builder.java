/**
 * 生成器（Builder）
 *
 * Intent
 * 
 * 封装一个对象的构造过程，并允许按步骤构造
 */

public class AbstractStringBuilder {
    protected char[] value;
    protected int count;
    public AbstractStringBuilder(int capacity) {
        count = 0;
        value = new char[capacity];
    }
    public AbstractStringBuilder append(char c) {
        ensureCapacityInternal(count+1);
        value[count++] = c;
        return this;
    }
    private void ensureCapacityInternal(int minimumCapacity) {
        if (minimumCapacity - value.length > 0)
            expandCapacity(int minimumCapacity);
    }
    void expandCapacity(int minimumCapacity) {
        int newCapacity = value.length * 2 + 2;
        if (newCapacity - minimumCapacity < 0)
            newCapacity = minimumCapacity;
        if (newCapacity < 0) {
            if (minimumCapacity < 0) 
                throw new OutOfMemoryError();
            newCapacity = Integer.MAX_VALUE;
        }
        value = Arrays.copyOf(value, newCapacity);
    }
}

public class StringBuilder extends AbstractStringBuilder {
    public StringBuilder() {
        super(16);
    }
    public String toString() {
        return new String(value, 0, count);
    }
}

public class Client {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        final int count = 26;
        for (int i=0; i<count; i++) {
            sb.append((char) ('a'+i));
        }
        System.out.println(sb.toString());
    }
}
