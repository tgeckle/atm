import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Filename: ATMFrame.java
 * Author: Tea
 * Date: Jun 7, 2016
 * Purpose: Creates a frame for the ATM GUI.
 * * * Defines behavior for each button in the GUI.
 */

public class ATMFrame extends JFrame {
    // Tracks currently selected account. 0 for Savings, 1 for Checking.
    int selectedAccount = 0;
    
    public ATMFrame() {
        // Begin JFrame construction
        super("ATM");
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize JPanels
        JPanel superPanel = new JPanel();
        JPanel loginPanel = new JPanel();
        JPanel buttons = new JPanel();
        JPanel buttonsRow2 = new JPanel();
        JPanel radios = new JPanel();
        JPanel field =  new JPanel();
        
        // Set JPanel layout
        superPanel.setLayout(new BorderLayout());
        loginPanel.setLayout(new FlowLayout());
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        buttonsRow2.setLayout(new FlowLayout());
        radios.setLayout(new FlowLayout());
        
        
        // Instantiat components
        JButton loginButton = new JButton("Login");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer To");
        JButton balanceButton = new JButton("View Balance");
        
        JRadioButton savings = new JRadioButton("Savings", true);
        JRadioButton checking = new JRadioButton("Checking");
        ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(savings);
        radioButtons.add(checking);
        
        JTextField input = new JTextField();
        input.setPreferredSize(new Dimension(200, 25));
        
        // Add components to the relevant panels
        buttons.add(withdrawButton, BorderLayout.WEST);
        buttons.add(depositButton, BorderLayout.EAST);
        buttonsRow2.add(transferButton);
        buttonsRow2.add(balanceButton);
        
        radios.add(savings);
        radios.add(checking);
        
        field.add(input);
        
        loginPanel.add(loginButton);
        
        // Instantiate Account class for use with listeners
        Account account = new Account(500, 0);
        
        // Add ActionListeners for each button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(loginPanel);
                add(superPanel, BorderLayout.NORTH);
                add(field, BorderLayout.CENTER);
                setLocationRelativeTo(null);
                pack();
            }
        });
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double withdrawalAmount = 0; 
                try {
                    withdrawalAmount = Integer.parseInt(input.getText());
                    String text = account.withdraw(selectedAccount, withdrawalAmount);
                    JOptionPane.showMessageDialog(null, text, "Withdraw", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch (NumberFormatException nfexc) {
                    JOptionPane.showMessageDialog(null, "Input is not a number."
                            + " Please try again", 
                            "Non-numerical Input", JOptionPane.ERROR_MESSAGE);
                }
                catch (InsufficientFundsException ifexc) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds found to complete "
                            + "transaction. Please try again", 
                            "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double depositAmount = 0;
                try {
                    depositAmount = Double.parseDouble(input.getText());
                    String text = account.deposit(selectedAccount, depositAmount);
                    JOptionPane.showMessageDialog(null, text, "Deposit", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch (NumberFormatException nfexc) {
                    JOptionPane.showMessageDialog(null, "Input is not a number."
                            + " Please try again", 
                            "Non-numerical Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        transferButton.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                double transferAmount = 0;
                
                try {
                    transferAmount = Double.parseDouble(input.getText());
                    String text = account.transfer(selectedAccount, transferAmount);
                    JOptionPane.showMessageDialog(null, text, "Transfer", 
                            JOptionPane.INFORMATION_MESSAGE);
                }
                catch (InsufficientFundsException exc) {
                    JOptionPane.showMessageDialog(null, "Insufficient funds found to complete "
                            + "transaction. Please try again", 
                            "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                    
                }
                catch (NumberFormatException nfexc) {
                    JOptionPane.showMessageDialog(null, "Input is not a number."
                            + " Please try again", 
                            "Non-numerical Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = account.displayBalance(selectedAccount);
                JOptionPane.showMessageDialog(null, text, "Balance", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
        }); 
        
        savings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedAccount = 0;
            }
        });
        
        checking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedAccount = 1;
            }
        });
        
        // Add subpanels to superpanels, add superpanels to frame
        superPanel.add(buttons, BorderLayout.NORTH);
        superPanel.add(buttonsRow2, BorderLayout.CENTER);
        superPanel.add(radios, BorderLayout.SOUTH);
        
        add(loginPanel, BorderLayout.NORTH);
        //add(field, BorderLayout.SOUTH);
        
        
    }

}
