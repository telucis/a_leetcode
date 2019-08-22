package common.java;

/**
 * @author karl.wy
 * @date 2019/08/14
 */
public class StaticTest {

    public static int k = 0;
    public static StaticTest t1 = new StaticTest("t1");
    public static StaticTest t2 = new StaticTest("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    {
        print("构造块");
    }

    static{
        print("静态块");
    }

    public StaticTest(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
        ++n;
        ++i;
    }

    public static int print(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
        ++i;
        return ++n;
    }
    public static void main(String[] args) {
        StaticTest t = new StaticTest("init");
    }

    /**
         1:j i=0 n=0
         2:构造块 i=1 n=1
         3:t1 i=2 n=2
         4:j i=3 n=3
         5:构造块 i=4 n=4
         6:t2 i=5 n=5
         7:i i=6 n=6
         8:静态块 i=7 n=99
         9:j i=8 n=100
         10:构造块 i=9 n=101
         11:init i=10 n=102
     */

}
