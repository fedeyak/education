package Transactions.src.main;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static final Bank bank = new Bank();
    private static final Map<String, Account> accounts = new Hashtable<>();

    public static void main(String[] args) {
        setBankAccounts();

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(Main::performTransactions));
        }
        System.out.println("Total bank balance: " + bank.getSumAllAccounts());

        threads.forEach(t -> t.start());
    }

    private static void performTransactions() {
        for (int i = 0; i < 30; i++) {
            String fromAccountNumber = String.valueOf(ThreadLocalRandom.current().nextInt(1, 100));
            String toAccountNumber = "";
            do {
                toAccountNumber = String.valueOf(ThreadLocalRandom.current().nextInt(1, 100));
            }
            while (fromAccountNumber.equals(toAccountNumber));
            long amount = ThreadLocalRandom.current().nextInt(0, 100_000);
            try {
                System.out.println("Transaction " + (i + 1) + ": Transferring $" + amount + " form acc. No. " + fromAccountNumber + " to acc. No. " + toAccountNumber);
                bank.transfer(fromAccountNumber, toAccountNumber, amount);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Thread " + Thread.currentThread() +": Total bank balance: " + bank.getSumAllAccounts());
    }

    private static void setBankAccounts() {
        for (int i = 1; i <= 100; i++) {
            accounts.put(String.valueOf(i), new Account(String.valueOf(i), i * 10_000));
        }
        bank.setAccounts(accounts);
    }
}
