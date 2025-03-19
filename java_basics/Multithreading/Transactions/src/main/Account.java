package Transactions.src.main;

public class Account {

    private final String accNumber;
    private final long money;
    private volatile boolean isBlocked = false;


    public Account(final String accNumber,final long money) {
        this.accNumber = accNumber;
        this.money = money;
    }

    public long getMoney() {
        return money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void blockAccount() {
        isBlocked = true;
    }
    public void unblockAccount() {isBlocked = false;}
}
