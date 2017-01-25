/**
 * Filename: InsufficientFunds.java
 * Author: Tea
 * Date: Jun 7, 2016
 * Purpose: For use with the ATM program when the user attempts to withdraw or 
 * transfer more funds than are available. 
 */

public class InsufficientFundsException extends Exception {
    
    public InsufficientFundsException() {
        super("Insufficient funds found to make transaction. Please try a smaller amount.");
    }

}
