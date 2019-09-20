import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author karl.wy
 * @date 2019/09/18
 */
public class Aop {

    interface A {
        public void a();
        public Object aaa(String s1, int i);
    }
    interface B {
        public void b();
    }

    public void test() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("test!!");
                return "returnTest";
            }
        };
        Object obj = Proxy.newProxyInstance(classLoader, new Class[]{A.class, B.class}, invocationHandler);
        A a = (A)obj;
        B b = (B)obj;
        a.toString();
        b.getClass();
        Object hello = a.aaa("Hello", 100);
        System.out.println(obj.getClass());
        System.out.println(hello);
    }

    /**
     * aop
     */
    interface Waiter {
        public void server();
    }
    class ManWaiter implements Waiter {
        @Override
        public void server() {
            System.out.println("servering");
        }
    }
    public class AopDemo {
        public void test1() {
            Waiter waiter = new ManWaiter();
            waiter.server();
        }
        public void test2() {
            Waiter manWaiter = new ManWaiter();
            ClassLoader classLoader = this.getClass().getClassLoader();
            Class[] interfaces = {Waiter.class};
            InvocationHandler invocationHandler = new WaiterInvocationHandler(manWaiter);
            Waiter waiter = (Waiter) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
            waiter.server();
        }
    }
    class WaiterInvocationHandler implements InvocationHandler {
        private Waiter waiter;

        public WaiterInvocationHandler(Waiter waiter) {
            this.waiter = waiter;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("hello");
            waiter.server();
            System.out.println("bye");
            return null;
        }
    }
    public void demoTest() {
        AopDemo aopDemo = new AopDemo();
        aopDemo.test2();
    }

    /**
     * 完善aop
     */
    public interface BeforeAdvice {
        public void before();
    }
    public interface AfterAdvice {
        public void after();
    }
    public class ProxyFactory {
        private Object targetObject;
        private BeforeAdvice beforeAdvice;
        private AfterAdvice afterAdvice;

        public Object createProxy() {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Class[] interfaces = targetObject.getClass().getInterfaces();
            InvocationHandler invocationHandler = new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (beforeAdvice!=null) {
                        beforeAdvice.before();
                    }
                    Object result = method.invoke(targetObject, args);
                    afterAdvice.after();
                    return result;
                }
            };
            Object proxyObject = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
            return proxyObject;
        }

        public void setTargetObject(Object targetObject) {
            this.targetObject = targetObject;
        }
        public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
            this.beforeAdvice = beforeAdvice;
        }
        public void setAfterAdvice(AfterAdvice afterAdvice) {
            this.afterAdvice = afterAdvice;
        }
    }
    public void finalDemo() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetObject(new ManWaiter());
        proxyFactory.setBeforeAdvice(new BeforeAdvice() {
            @Override
            public void before() {
                System.out.println("hello(final)");
            }
        });
        proxyFactory.setAfterAdvice(new AfterAdvice() {
            @Override
            public void after() {
                System.out.println("bye(final)");
            }
        });
        Waiter waiter = (Waiter)proxyFactory.createProxy();
        waiter.server();
    }


    public static void main(String[] args) {
        Aop test = new Aop();
        //test.test();
        //test.demoTest();
        test.finalDemo();
    }

}
