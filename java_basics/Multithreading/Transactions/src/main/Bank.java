package Transactions.src.main;

import Transactions.src.main.exceptions.BlockedAccountException;
import Transactions.src.main.exceptions.NoSuchAccountException;
import Transactions.src.main.exceptions.NotEnoughMoneyException;

import java.util.Map;
import java.util.Random;

public class Bank {

    private static final int SUSPICIOUS_AMOUNT = 50_000;
    public Map<String, Account> accounts;
    private final Random random = new Random();

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }
    

    public synchronized void transfer(final String fromAccountNum, final String toAccountNum, final long amount) throws Exception {
        boolean isFraud = false;

        final String fromAccountKey = accountFounder(fromAccountNum);
        final String toAccountKey = accountFounder(toAccountNum);

        if (getBalance(fromAccountKey) < amount) {
            throw new NotEnoughMoneyException("You have insufficient balance to perform the transaction.");
        }

        if (amount > SUSPICIOUS_AMOUNT) {
            try {
                isFraud = isFraud(fromAccountNum, toAccountNum, amount);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (isFraud) {
            throw new BlockedAccountException("You are engaged in suspicious transaction. " +
                    "Your account is currently blocked.");
        }

        accounts.put(fromAccountKey, new Account(
                accounts.get(fromAccountKey).getAccNumber(),
                accounts.get(fromAccountKey).getMoney() - amount));

        accounts.put(toAccountKey, new Account(
                accounts.get(toAccountKey).getAccNumber(),
                accounts.get(toAccountKey).getMoney() + amount));
    }

    public synchronized long getBalance(String key) {
        return accounts.get(key).getMoney();
    }

    public synchronized long getSumAllAccounts() {
        long sum = 0;
        for (Map.Entry<String, Account> account : accounts.entrySet()) {
            sum = sum + account.getValue().getMoney();
        }
        return sum;
    }

    // This method is not synchronised because it is called only from a synchronised method
    public String accountFounder(String accountNumber) throws Exception {
        String key = "";
        boolean accountIsFound = false;
        for (Map.Entry<String, Account> account : accounts.entrySet()) {
            if (account.getValue().getAccNumber().equals(accountNumber)) {
                key = account.getKey();
                accountIsFound = true;
                break;
            }
        }
        if (!accountIsFound) {
            throw new NoSuchAccountException("Account No. " + accountNumber + " is not found.");
        }
        if (accounts.get(key).isBlocked()) {
            throw new BlockedAccountException("The account " + accountNumber + " is blocked.\n" +
                    "Transactions are not allowed.");
        }
        return key;
    }
}
