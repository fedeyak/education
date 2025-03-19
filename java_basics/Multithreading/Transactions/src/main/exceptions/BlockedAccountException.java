package Transactions.src.main.exceptions;

public class BlockedAccountException extends Exception{

    public BlockedAccountException(String message) {
        super(message);
    }
}
