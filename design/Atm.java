
/**
 * 如何设计一个ATM Machine?
1. Clarify
    ATM机的Input和Output是什么?
        Assumption: Input 是卡, output 是现金
    Input 有什么限制吗?
        Assumption: 只能是debit card
    output 有什么限制吗?
        Assumption: 只能是20美元的倍数
    Output 会不足吗?
        Assumption: ATM永远有足够的余额 (Bonus: 这里可以考虑如果不够的话应该怎么处理)
    Input 有什么特点?
        Assumption: 一张卡可能有多个账户 (Bonus: 这个不太容易想到， 没想到也没关系，我们可以先按照一个account来做)
2. Core objects
    Debit Card
    ATM Machine
    Account
3. Use Case
    Take debit card
    Authorization / Log in
    Select Account (Optional 如果一张卡有多个account)
    Check balance
    Deposite money
    Withdraw money
    Log out
4. Classes
    ATM Machine
        float balance
        Session currentSession
        -
        void takeDebitCard(DebitCard card)
        List<Account> login(String passcode)
        void selectAccount(Account account)
        float checkBalance()
        void depositeMoney(float amount)
        float withDrawMoney(float amount)
        void logOut()
    Session
        DebitCard currentDebittCard
        Account currentAccount
    Account
        void depositeMoney(float amount)
        withDrawMoney(float amount)

1. 增加各式的Exceptions

2. 对于ATM机，有许多种Design pattern可以适用，其中比较合适的就有我们《OOD面向对象专题》课上讲过的State Design Pattern, 大家可以自己思考有哪些合适的states。

 */

public interface State {

}

public NoCardState implements State {}
public NoLoginState implements State {}
public SelectAccountState implements State {}
public LoginState implements State {}

public NoBalanceState implements State {}

public class ATM {
    private float balance;
    private Session session;
    private State state;

    private State noCardState;
    private State noLoginState;
    private State selectAccountState;
    private State loginState;
    private State noBalanceState;

    public ATM(float amount) {
        balance = amount;
        noCardState = new NoCardState(this);
        noLoginState = new NoLoginState(this);
        selectAccountState = new SelectAccountState(this);
        loginState = new LoginState(this);
        noBalanceState = new NoBalanceState(this);

        if (amount>0) this.state = noCardState;
        else this.state = noBalanceState;
    }
    public State getState() {return this.state;}
    public void setState(State state) {this.state = state;}

    public void takeDebitCard(DebitCard card) {

    }

    public List<Account> login(String passcode) {

    }

    public void selectAccount(Account account) {

    }

    public float checkBalance() {

    }

    public void depositeMoney(float amount) {

    }

    public float withDrawMoney(float amount) {

    }

    public void logOut() {

    }
}

public class Session {
    DebitCard debitCard;
    Account account;
}

public class DebitCard {
    private String cardCode;
    private String passCode;
    private List<Account> accountList;
}

public class Account {
    private String accountId;
    private float balance;
}

public class NoBalanceException extends Exception {
    public NoBalanceException() {
        super("no balance");
    }
}








