/**
 * 复合模式
 */


/**
 * 鸭子类
 */
public interface Quackable extends QuackObservable{
    public void quack();
}
public class MallardDuck implements Quackable {
    Observable observable;
    public MallardDuck() {
        observable = new Observable(this);
    }
    public void quack() {
        notifyObservers();
        System.out.println("Quack");
    }
    public void registerObserver(Observer observer) {
        Observable.registerObserver(observer);
    }
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
public class RedheadDuck implements Quackable {
    public void quack() {
        System.out.println("Quack");
    }
}
public class DuckCall implements Quackable {
    public void quack() {
        System.out.println("Kwak");
    }
}
public class RubberDuck implements Quackable {
    public void quack() {
        System.out.println("Squeak");
    }
}
/**
 * 观察者
 * 统计个别群体
 */
public interface QuackObservable {
    public void registerObserver(Observer observer);
    public void notifyObservers();
}
public class Observable implements QuackObservable {
    ArrayList observers = new ArrayList<>();
    QuackObservable duck;
    public Observable(QuackObservable duck) {this.duck = duck;}
    public void registerObserver(Observer observer) {observers.add(observer);}
    public void notifyObservers() {
        Iterator iterator = observers.iterator();
        while (iterator.hasNext()) {
            Observer observer = (Observer)iterator.next();
            observer.update(duck);
        }
    }
}
public interface Observer {
    public void update(QuackObservable duck);
}
public class Quackologist implements Observer {
    public void update(QuackObservable duck) {
        System.out.println("Quackologist: " + duck + " just quacked.");
    }
}
/**
 * 抽象工厂模式
 * 生产不同类型的产品家族
 */
public abstract class AbstractDuckFactory {
    public abstract Quackable createMallardDuck();
    public abstract Quackable createRedHeadDuck();
    public abstract Quackable createDuckCall();
    public abstract Quackable createRubberDuck();
}
public class DuckFactory extends AbstractDuckFactory {
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }
    public Quackable createRedHeadDuck() {
        return new RedheadDuck();
    }
    public Quackable createDuckCall() {
        return new DuckCall();
    }
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
public class CountingDuckFactory extends AbstractDuckFactory {
    public Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }
    public Quackable createRedHeadDuck() {
        return new QuackCounter(new RedheadDuck());
    }
    public Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
/**
 * 装饰器模式
 * 统计次数
 */
public class QuackCounter implements Quackable {
    Quackable duck;
    static int numberOfQuacks;
    public QuackCounter(Quackable duck) {this.duck = duck;}
    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }
    public static int getQuacks() {return numberOfQuacks;}
}
/**
 * 适配器模式
 * 鹅
 */
public class Goose {
    public void honk() {
        System.out.println("Honk");
    }
}
public class GooseAdapter implements Quackable {
    Goose goose;
    public GooseAdapter(Goose goose) {this.goose = goose;}
    public void quack() {goose.honk();}
}
/**
 * 组合模式
 * 管理一群鸭子
 */
public class Flock implements Quackable {
    ArrayList quackers = new ArrayList<>();
    public void add(Quackable quacker) {quackers.add(quacker);}
    public void quack() {
        Iterator iterator = quackers.iterator();
        while (iterator.hasNext()) {
            Quackable quacker = (Quackable)iterator.next();
            quacker.quack();
        }
    }
}
/**
 * 模拟器
 */
public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();
        simulator.simulate(duckFactory);
    }
    void simulate(AbstractDuckFactory duckFactory) {
        Quackable redheadDuck = duckFactory.createRedHeadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable gooseDuck = new GooseAdapter(new Goose());

        System.out.println("Duck Simulator");

        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        Quackable mallard1 = duckFactory.createMallardDuck();
        Quackable mallard2 = duckFactory.createMallardDuck();
        Quackable mallard3 = duckFactory.createMallardDuck();
        Quackable mallard4 = duckFactory.createMallardDuck();
        Flock flockOfMallards = new Flock();
        flockOfMallards.add(mallard1);
        flockOfMallards.add(mallard2);
        flockOfMallards.add(mallard3);
        flockOfMallards.add(mallard4);

        flockOfDucks.add(flockOfMallards);

        System.out.println("Duck Simulator: With Observer");
        Quackologist quackologist = new Quackologist();
        flockOfDucks.registerObserver(quackologist);

        System.out.println("Duck Simulator: Whole Flock Simulation");
        simulate(flockOfDucks);
        System.out.println("Duck Simulator: Mallar Flock Simulation");
        simulate(flockOfMallards);
    }
    void simulate(Quackable duck) {
        duck.quack();
    }
}
