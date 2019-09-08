/**
 * 单例（Singleton）
 *
 * Intent
 * 
 * 确保一个类只有一个实例，并提供该实例的全局访问点。
 *
 * Class Diagram
 * 
 * 使用一个私有构造函数、一个私有静态变量以及一个公有静态函数来实现。
 * 
 * 私有构造函数保证了不能通过构造函数来创建对象实例，只能通过公有静态函数返回唯一的私有静态变量。
 */

/**
 * 懒汉式 - 线程不安全
 */
public class Singleton {
    private static Singleton uniqueInstance;
    private Singleton() {}
    public static Singleton getUniqueIsntance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}

/**
 * 饿汉式 - 线程安全
 */
public class Singleton {
    private static Singleton uniqueInstance = new Singleton();
    private Singleton() {}
    public static Singleton getUniqueIsntance() {
        return uniqueInstance;
    }
}

/**
 * 懒汉式 - 线程安全
 */
public class Singleton {
    private static Singleton uniqueInstance;
    private Singleton() {}
    public static synchronized Singleton getUniqueIsntance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}

/**
 * 双重校验锁 - 线程安全
 *
 * uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
 * 
 * 为 uniqueInstance 分配内存空间
 * 初始化 uniqueInstance
 * 将 uniqueInstance 指向分配的内存地址
 *
 * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行
 */
public class Singleton {
    private volatile static Singleton uniqueInstance;
    private Singleton() {}
    public static Singleton getUniqueIsntance() { 
        if (uniqueInstance == null) {
            synchronized(Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}

/**
 * 内部静态类
 *
 * 当 Singleton 类被加载时，静态内部类 SingletonHolder 没有被加载进内存。
 * 只有当调用 getUniqueInstance() 方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载
 * 此时初始化 INSTANCE 实例，
 * 并且 JVM 能确保 INSTANCE 只被实例化一次
 */
public class Singleton {
    private Singleton() {}
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getUniqueIsntance() { 
        return SingletonHolder.INSTANCE;
    }
}

/**
 * 枚举实现
 *
 * 该实现可以防止反射攻击。
 * 在其它实现中，通过 setAccessible() 方法可以将私有构造函数的访问级别设置为 public，
 * 然后调用构造函数从而实例化对象，
 * 如果要防止这种攻击，需要在构造函数中添加防止多次实例化的代码。
 * 该实现是由 JVM 保证只会实例化一次，因此不会出现上述的反射攻击。
 *
 * 该实现在多次序列化和序列化之后，不会得到多个实例。
 * 而其它实现需要使用 transient 修饰所有字段，并且实现序列化和反序列化的方法。
 */

public enum Singleton {
    INSTANCE;
    private String objName;
    public String getObjName() {return objName}
    public void setObjName(String objName) {this.objName = objName;}

    public static void main(String[] args) {

        // 单例测试
        Singleton firstSingleton = Singleton.INSTANCE;
        firstSingleton.setObjName("firstName");
        System.out.println(firstSingleton.getObjName());
        Singleton secondSingleton = Singleton.INSTANCE;
        secondSingleton.setObjName("secondName");
        System.out.println(firstSingleton.getObjName());
        System.out.println(secondSingleton.getObjName());

        // 反射获取实例测试
        try {
            Singleton[] enumConstants = Singleton.class.getEnumConstants();
            for (Singleton enumConstant : enumConstants) {
                System.out.println(enumConstant.getObjName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * firstName
     * secondName
     * secondName
     * secondName
     */
}

