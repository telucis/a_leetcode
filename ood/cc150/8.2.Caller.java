public class CallHandler {
    private static CallHandler instance;
    public static CallHandler getInstance() {
        if (instance == null) instance = new CallHandler();
        return instance;
    }

    private final int LEVELS = 3;
    private final int NUM_RESPONDENTS = 10;
    private final int NUM_MANAGERS = 4;
    private final int NUM_DIRECTORS = 2;
    List<List<Employee>> employeeLevels;
    List<List<Call>> callQueues;
    
    protected CallHandler() {
        employeeLevels = new ArrayList<List<Employee>>(LEVELS);
        callQueues = new ArrayList<List<Call>>(LEVELS);
        // Create respondents.
        ArrayList<Employee> respondents = new ArrayList<Employee>(NUM_RESPONDENTS);
        for (int k = 0; k < NUM_RESPONDENTS - 1; k++) {
            respondents.add(new Respondent());
        }
        employeeLevels.add(respondents);
        // Create managers.
        ArrayList<Employee> managers = new ArrayList<Employee>(NUM_MANAGERS);
        managers.add(new Manager());
        employeeLevels.add(managers);
        // Create directors.
        ArrayList<Employee> directors = new ArrayList<Employee>(NUM_DIRECTORS);
        directors.add(new Director());
        employeeLevels.add(directors);
    }

    public Employee getHandlerForCall(Call call) {
        for (int level = call.getRank(); level<LEVELS-1; level++) {
            List<Employee> employeeLevel = employeeLevels.get(level);
            for (Employee emp : employeeLevel) {
                if (emp.isFree()) {
                    return emp;
                }
            }
        }
        return null;
    }

    public void dispatchCall(Caller caller) {
        Call call = new Call(caller);
        dispatchCall(call);
    }

    public void dispatchCall(Call call) {
        Employee emp = getHandlerForCall(call);
        if (emp != null) {
            emp.receiveCall(call);
            call.setHandler(emp);
        } else {
            call.reply("Please wait for free employee to reply");
            callQueues.get(call.getRank().getValue()).add(call);
        }
    }

    public boolean assignCall(Employee emp) {
        for (int rank=emp.getRank().getValue(); rank>=0; rank--) {
            List<Call> que = callQueues.get(rank);

            if (que.size() > 0) {
                Call call = que.remove(0);
                if (call != null) {
                    emp.receiveCall(call);
                    return true;
                }
            }
            return false;
        }
    }
}

public enum Rank {
    Responder(0),
    Manager(1),
    Director(2);
    private int value;
    private Rank(int v) {value = v;}
    public int getValue() {return value;}
}

public class Call {
    private Rank rank;
    private Caller caller;
    private Employee handler;
    public Call(Caller c) {
        rank = Rank.Responder;
        caller = c;
    }
    public void setHander(Employee e) {
        this.handler = e;
    }
    public void reply(String msg) {
        System.out.println(msg);
    }
    public void disconnect()
    public Rank getRank() return rank;
    public void setRank(Rank r) {rank = r;}
    public Rank incrementRank() {
        if (rank == Rank.Responder) rank = Rank.Manager;
        else if (rank = Rank.Manager) rank = Rank.Director;
        return rank;
    }
}

public class Caller {
    private String name;
    private int userId;
    public Caller (int id, String name) {
        this.name = name;
        this.id = id;
    }
}

public abstract class Employee {
    public Employee(){}
    private Call currentCall = null;
    protected Rank rank;
    public void receiveCall(Call call) {currentCall = call;}
    public void callCompeted() {
        if (currentCall!=null) {
            currentCall.disconnect();
            currentCall = null;
        }
        assignNewCall();
    }
    // can't been resolved
    public void escalateReassign() {
        if (currentCall != null) {
            currentCall.incrementRank();
            CallHandler.getInstance().dispatchCall(currentCall);
            currentCall = null
        }
        assignNewCall();
    }
    public boolean assignNewCall() {
        if (!isFree()) return false;
        return CallHandler.getInstance().assignCall(this);
    }
    public boolean isFree() {return currentCall!=null;}
    public Rank getRank() {return rank;}
}

public class Director extends Employee {
    public Director() {
        rank = Rank.Director;
    }
}

public class Manager extends Employee {
    public Manager() {
        rank = Rank.Manager;
    }
}

public class Respondent extends Employee {
    public Respondent() {
        rank = Rank.Responder;
    }
}