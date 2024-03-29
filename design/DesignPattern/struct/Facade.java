/**
 * 外观（Facade）
 *
 * Intent
 * 
 * 提供了一个统一的接口，用来访问子系统中的一群接口，从而让子系统更容易使用。
 *
 * Implementation
 * 
 * 观看电影需要操作很多电器，使用外观模式实现一键看电影功能。
 */

public class SubSystem {
    public void turnOnTV() {
        System.out.println("turnOnTV()");
    }

    public void setCD(String cd) {
        System.out.println("setCD( " + cd + " )");
    }

    public void startWatching(){
        System.out.println("startWatching()");
    }
}

public class Facade {
    private SubSystem subSystem = new SubSystem();
    public void watchMovie() {
        subSystem.turnOnTV();
        subSystem.setCD("a movie");
        subSystem.startWatching();
    }
}

public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.watchMovie();
    }
}