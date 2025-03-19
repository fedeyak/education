package Transactions.src.test;

import java.util.concurrent.ThreadLocalRandom;
import Transactions.src.main.Account;
import Transactions.src.main.Bank;
import Transactions.src.main.exceptions.BlockedAccountException;
import Transactions.src.main.exceptions.NoSuchAccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class  TransactionsTest {

    Bank bank = new Bank();

    @BeforeEach
    public void setAccounts() {
        Map<String, Account> accounts = new Hashtable<>();
        for (int i = 1; i <= 100; i++){
            accounts.put(String.valueOf(i), new Account(String.valueOf(i), i * 10_000));
        }
        bank.setAccounts(accounts);
    }

    @Test
    public void mainTest() {

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            String fromAccountNumber = String.valueOf(ThreadLocalRandom.current().nextInt(1, 100));
            String toAccountNumber = "";
            do {
                toAccountNumber = String.valueOf(ThreadLocalRandom.current().nextInt(1, 100));
            }
            while (fromAccountNumber.equals(toAccountNumber));
            long amount = ThreadLocalRandom.current().nextInt(0, 100_000);
            try {
                bank.transfer(fromAccountNumber, toAccountNumber, amount);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
//            System.out.println(i);
        }
//        System.out.println(fromAccountNumber);
//        System.out.println(toAccountNumber);
//        System.out.println(randAmount);

        System.out.println(System.currentTimeMillis() - start);

    }

    @Test
    public void transferTest() throws Exception {
        assertEquals(40_000, bank.getBalance("4"));
        assertEquals(50_000, bank.getBalance("5"));
        assertEquals(50_500_000, bank.getSumAllAccounts());

        bank.transfer("4", "5", 20_000);
        assertEquals(20_000, bank.getBalance("4"));
        assertEquals(70_000, bank.getBalance("5"));
        assertEquals(50_500_000, bank.getSumAllAccounts());

//        bank.transfer("4", "5", 100_000);
//        assertEquals(10_000, bank.getBalance("4"));
//        assertEquals(60_000, bank.getBalance("5"));
//        assertEquals(360_000, bank.getSumAllAccounts());
    }

    @Test
    public void getBalanceTest() {
        assertEquals(10_000, bank.getBalance("1"));
        assertEquals(20_000, bank.getBalance("2"));
        assertEquals(90_000, bank.getBalance("9"));
        assertNotEquals(80_000, bank.getBalance("2"));
        assertNotEquals(10_000, bank.getBalance("5"));
    }

    @Test
    public void getSumAllAccountsTest(){
        assertEquals(50_500_000, bank.getSumAllAccounts());
    }

    @Test
    public void accountFounderTest() throws Exception {
        assertEquals("1", bank.accountFounder("1"));

        NoSuchAccountException thrown1 = Assertions.assertThrows(NoSuchAccountException.class,
                () -> bank.accountFounder("a"));

        bank.accounts.get("5").blockAccount();
        BlockedAccountException thrown2 = Assertions.assertThrows(BlockedAccountException.class,
                () -> bank.accountFounder("5"));
    }
}
