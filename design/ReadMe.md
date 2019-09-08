https://www.1point3acres.com/bbs/thread-541834-1-1.html
https://www.1point3acres.com/bbs/thread-494518-1-1.html
https://www.1point3acres.com/bbs/thread-431401-1-1.html
https://www.1point3acres.com/bbs/thread-208829-1-1.html

https://docs.mongodb.com/manual/core/sharding-shard-key/#sh%E2%80%8D%E2%80%8D%E2%80%8D%E2%80%8D%E2%80%8C%E2%80%8C%E2%80%8D%E2%80%8C%E2%80%8D%E2%80%8C%E2%80%8C%E2%80%8D%E2%80%8D%E2%80%8C%E2%80%8C%E2%80%8C%E2%80%8C%E2%80%8D%E2%80%8Darding-shard-key-selection

https://www.1point3acres.com/bbs/thread-436777-1-1.html
https://www.1point3acres.com/bbs/thread-537998-1-1.html


* 管理类OOD
    - 停车场问题  Parking lot
    - 餐厅管理问题  Restaurant
* 预定类OOD 
    - 酒店预定系统  Hotel Reservation
    - 航空机票预定系统  Airline Ticket
* 实物类OOD
    - Vending machine
    - Juke box
* 游戏棋牌类OOD
    - Black Jack
    - Chinese chess

* SOLID设计原则
    - S - Single-responsiblity principle  **单一责任原则**（一个类有且只有一个更改的原因）
        + 引起类变化的因素永远不要多于一个
        + 也就是说一个类有且只有一个职责。
        ```java
            public class AreaCalculator {
                private float result;
                public float getResult() {return this.result;}
                public float calculateArea(Triangle t) {this.result = h*b/2;}
            }
            public class Printer {
                public printInJson(float number) {
                    jsonPrinter.initialize();
                    jsonPrinter.print(this.result);
                    jsonPrinter.close();
                }
            }
        ```
    - O - Open-closed principle  **开发封闭原则**（能够不更改类而扩展类的行为）
        + 「对扩展开放」指的是设计类时要考虑到新需求提出是类可以增加新功能
        + 「对修改关闭」指的是一旦一个类开发完成，除了修正 BUG 就不要再去修改它
        + 通常可以通过依赖关系抽象实现开闭原则，比如 interface（接口） 或 abstract（抽象类）而不是具体类，通过创建新的类实现它们来增加功能
        ```java
            // bad design
            public class AreaCalculator {
                public float calculateArea(Triangle t) {}
                public float calculateArea(Rectangle r) {}
            }
            // good design
            public interface Shape {public float getArea();}
            public class Triangle implements Shape {
                public float getArea() {return b*h/2;}
            }
        ```
        ```java
            // bad design
            public function payInit($payType){
                $payment = null;
                if(true == $payType){
                    // 微信支付
                    $payment = acceptWechat($total);
                }else{
                    // 支付宝支付
                    $payment = acceptAlipay($total);
                }
                return $payment;
            }
            // good design
            interface PaymentMethod{ public function accept($total) }

            public function checkOut(PaymentMethod $pm, $total){
                return $pm->accept($total);
            }
        ```
    - L - Liskov substitution principle **里氏替换原则**（派生类可以替换基类使用）
        + 当一个子类的实例应该能够替换任何其父类的实例时，它们之间才具有IS-A关系
        + 若违反 LSP 进行设计，将导致不明确的行为产生
            * 如果没有 LSP，类继承就会混乱；
            * 如果子类作为一个参数传递给方法，将会出现未知行为；
            * 如果没有 LSP，适用与基类的单元测试将不能成功用于测试子类
        ```java
            /**
             * 一个违反LSP的典型例子是 Square（正方形） 类派生于 Rectangle（长方形） 类。
             * Square 类总是假定宽度与高度相等。
             * 如果一个正方形对象用于期望一个长方形的上下文中，可能会出现意外行为，
             * 因为一个正方形的宽高不能(或者说不应该)被独立修改。
             */
        ```
    - I - Interface segregation principle **接口分离原则**（使用客户端特定的细粒度接口）
        + 不要强迫客户端（泛指调用者）去依赖那些他们不使用的接口
        + 当我们使用非内聚的接口时，ISP 原则指导我们创建多个较小的高内聚接口。
        ```java
            // bad design
            public class Shape {
                abstract public float calculateVolumn();
                abstract public float calculateArea();
            }
            public class Rectangle extends Shape{}
            public class Cube extends Shape{}
        ```
        ```java
            // bad design
            interface Messenger {
                askForCard();
                tellInvalidCard();
                askForPin();
                tellInvalidPin();
                tellCardWasSiezed();
                askForAccount();
                tellNotEnoughMoneyInAccount();
                tellAmountDeposited();
                tellBalance();
            }
            // good design
            interface LoginMessenger {
              askForCard();
              tellInvalidCard();
              askForPin();
              tellInvalidPin(); 
            }
            interface WithdrawalMessenger {
              tellNotEnoughMoneyInAccount();
              askForFeeConfirmation();
            }
              
            publc class EnglishMessenger implements LoginMessenger, WithdrawalMessenger {
              ...   
            }
        ```
    - D - Dependency Inversion Principle **依赖反转原则**（依赖抽象而不是具体的实现）
        + 高层模块不应该依赖底层模块，两者都应该依赖其抽象
        + 抽象不应该依赖于细节，细节应该依赖于抽象
        ```java
            public interface Shape{ public float getArea();}
            public class Triangle implements Shape {
                public float getArea() { return b*n/2;}
            }
            public class AreaCalculator {
                private float result;
                public float getResult() {return this.result;}
                public float calculateArea(Shape s) {this.result = s.getResult();}
            }
        ```
        ```java
            interface Reader { getchar(); }
            interface Writer { putchar($c)}
            
            class CharCopier {
                public function copy(Reader reader, Writer writer) {
                    $c;
                    while ((c = reader.getchar()) != EOF) {
                        writer.putchar();
                    }
                }
            }
            public Keyboard implements Reader {...}
            public Printer implements Writer {...}
        ```