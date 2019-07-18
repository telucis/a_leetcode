package easy;

/**
 * @author karl.wy
 * @date 2019/07/11
 */
public class Test {

    public Object instance = null;
    public static void main(String[] args) {
        Test objA = new Test();
        Test objB = new Test();

        System.out.println(objA.instance);
        //
        //objB.instance = objA;
        //objA.instance = objB;
        //
        //objA = null;
        //objB = null;

        System.gc();
    }
}

