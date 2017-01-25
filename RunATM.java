import javax.swing.*;

/**
 * Filename: RunATM.java
 * Author: Tea
 * Date: Jun 7, 2016
 * Purpose: Instantiates and runs the ATMFrame class to create the ATM GUI.
 */

public class RunATM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ATMFrame frame = new ATMFrame();
        frame.pack();
        frame.setVisible(true);
    }
    
}
