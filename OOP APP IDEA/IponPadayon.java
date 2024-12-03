import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class IponPadayonTest {

    private static double wallet = 0;
    private static double expenses = 0;
    private static ArrayList<String> expenseList = new ArrayList<>();
    private static ArrayList<String[]> users = new ArrayList<>();  // Stores [username, password]

    public static void main(String[] args) {
        loadData();
        loadUserData();  // Load user data from userdata.txt
        SwingUtilities.invokeLater(LoginScreen::new); // Show login screen first
    }

    // Load user data from userdata.txt
    private static void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] user = line.split(":");
                if (user.length == 2) {
                    users.add(user);  // Store each user as a [username, password] pair
                }
            }
        } catch (IOException e) {
            System.out.println("No previous user data found. Starting fresh.");
        }
    }

    // Save user data to userdata.txt
    private static void saveUserData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("userdata.txt"))) {
            for (String[] user : users) {
                writer.println(user[0] + ":" + user[1]);  // Save username and password separated by ":"
            }
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    // Login Screen Class
    static class LoginScreen {
        
        LoginScreen() {
            JFrame frame = new JFrame("IponPadayon");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            
            ImageIcon logo = new ImageIcon("iponpadayon.png");
            frame.setIconImage(logo.getImage());

            JPanel loginPanel = new JPanel();
            loginPanel.setLayout(new GridLayout(3, 2));
            loginPanel.setBackground(new Color(107,189,142));

            JLabel userLabel = new JLabel("Username:");
            userLabel.setForeground(Color.WHITE);
            JTextField userField = new JTextField();
            userField.setBackground(Color.WHITE);
            userField.setForeground(Color.BLACK);

            JLabel passLabel = new JLabel("Password:");
            passLabel.setForeground(Color.WHITE);
            JPasswordField passField = new JPasswordField();
            passField.setBackground(Color.WHITE);
            passField.setForeground(Color.BLACK);

            JButton loginButton = new JButton("Login");
            loginButton.setBackground(new Color(64,237,139));
            loginButton.setForeground(Color.WHITE);

            JButton signUpButton = new JButton("Sign Up");
            signUpButton.setBackground(new Color(64,237,139));
            signUpButton.setForeground(Color.WHITE);

            loginPanel.add(userLabel);
            loginPanel.add(userField);
            loginPanel.add(passLabel);
            loginPanel.add(passField);
            loginPanel.add(signUpButton);
            loginPanel.add(loginButton);

            frame.add(loginPanel);
            frame.setVisible(true);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = userField.getText();
                    String password = new String(passField.getPassword());

                    // Check if the entered username and password match any saved user
                    boolean isAuthenticated = false;
                    for (String[] user : users) {
                        if (user[0].equals(username) && user[1].equals(password)) {
                            isAuthenticated = true;
                            break;
                        }
                    }

                    if (isAuthenticated) {
                        JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();  // Close login window
                        SwingUtilities.invokeLater(MainMenu::new);  // Show main menu after login
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            signUpButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open registration form
                    JFrame registerFrame = new JFrame("Sign Up");
                    registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    registerFrame.setSize(300, 200);
                    registerFrame.setLocationRelativeTo(null);
                    registerFrame.setResizable(false);
                    
                    ImageIcon logo = new ImageIcon("iponpadayon.png");
                    registerFrame.setIconImage(logo.getImage());

                    JPanel registerPanel = new JPanel(new GridLayout(3, 2));
                    registerPanel.setBackground(new Color(107,189,142));

                    JLabel registerUsernameLabel = new JLabel("Username:");
                    registerUsernameLabel.setForeground(Color.WHITE);
                    JTextField registerUsernameField = new JTextField();
                    registerUsernameField.setBackground(Color.WHITE);
                    registerUsernameField.setForeground(Color.darkGray);

                    JLabel registerPasswordLabel = new JLabel("Password:");
                    registerPasswordLabel.setForeground(Color.WHITE);
                    JPasswordField registerPasswordField = new JPasswordField();
                    registerPasswordField.setBackground(Color.WHITE);
                    registerPasswordField.setForeground(Color.darkGray);

                    JButton registerButton = new JButton("Register");
                    registerButton.setBackground(new Color(64,237,139));
                    registerButton.setForeground(Color.WHITE);

                    registerPanel.add(registerUsernameLabel);
                    registerPanel.add(registerUsernameField);
                    registerPanel.add(registerPasswordLabel);
                    registerPanel.add(registerPasswordField);
                    registerPanel.add(new JLabel()); // Empty space for alignment
                    registerPanel.add(registerButton);

                    registerFrame.add(registerPanel);
                    registerFrame.setVisible(true);

                    registerButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String newUsername = registerUsernameField.getText();
                            String newPassword = new String(registerPasswordField.getPassword());

                            // Check if username already exists
                            boolean usernameExists = false;
                            for (String[] user : users) {
                                if (user[0].equals(newUsername)) {
                                    usernameExists = true;
                                    break;
                                }
                            }

                            if (usernameExists) {
                                JOptionPane.showMessageDialog(registerFrame, "Username already exists. Try a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                // Save new user to the list and file
                                users.add(new String[]{newUsername, newPassword});
                                saveUserData();
                                JOptionPane.showMessageDialog(registerFrame, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                registerFrame.dispose();
                            }
                        }
                    });
                }
            });
        }
    }

    // Main Menu Class
    static class MainMenu {
        
        JFrame frame;

        MainMenu() {
            frame = new JFrame("IponPadayon");
            frame.setSize(450, 350);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(new Color(107, 189, 142));
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setLayout(null);

            ImageIcon logo = new ImageIcon("iponpadayon.png");
            frame.setIconImage(logo.getImage());

            // Buttons to interact with the app
            JButton updateMoneyButton = new JButton("Update Wallet");
            updateMoneyButton.setBounds(125, 34, 200, 50);
            updateMoneyButton.setBackground(new Color(64, 237, 139));
            updateMoneyButton.setFocusable(false);

            JButton addExpenseButton = new JButton("+ Add Expense");
            addExpenseButton.setBounds(125, 117, 200, 50);
            addExpenseButton.setBackground(new Color(64, 164, 237));
            addExpenseButton.setFocusable(false);

            JButton summaryButton = new JButton("Financial Summary");
            summaryButton.setBounds(125, 200, 200, 50);
            summaryButton.setBackground(new Color(255, 255, 255));
            summaryButton.setFocusable(false);

            frame.add(updateMoneyButton);
            frame.add(addExpenseButton);
            frame.add(summaryButton);

            // Action listeners for the buttons
            updateMoneyButton.addActionListener(e -> {
                String input = JOptionPane.showInputDialog("Enter new wallet balance:");
                if (input == null) {
                    return;
                }
                try {
                    wallet = parseAmount(input); // Parse and round the input
                    saveData();
                    JOptionPane.showMessageDialog(frame, "Wallet balance updated successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
                }
            });

            addExpenseButton.addActionListener(e -> {
                String description = JOptionPane.showInputDialog("Enter expense description:");
                if (description == null) {
                    return;
                }

                String amountStr = JOptionPane.showInputDialog("Enter expense amount:");
                if (amountStr == null) {
                    return;
                }

                // Check if the input doesn't contain a decimal and append ".00" if needed
                if (!amountStr.contains(".")) {
                    amountStr += ".00";  // Default to .00 if no decimal is entered
                }

                try {
                    double amount = parseAmount(amountStr); // Parse and round the input
                    expenses += amount;
                    expenseList.add(description + " - $" + String.format("%.2f", amount)); // Display with two decimals
                    expenses = Math.round(expenses * 100.0) / 100.0;
                    saveData();
                    JOptionPane.showMessageDialog(frame, "Expense added successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
                }
            });

            summaryButton.addActionListener(e -> {
                frame.dispose();
                new WalletDetails();
            });

            frame.setVisible(true);
        }

        // Helper function to parse and round the amount to two decimal places
        private double parseAmount(String input) {
            // If the input is a whole number, append ".00"
            if (!input.contains(".")) {
                input = input + ".00";
            }

            // Parse the amount and round to 2 decimal places
            double amount = Double.parseDouble(input);
            return Math.round(amount * 100.0) / 100.0;
        }
    }

    static class WalletDetails {

        JFrame frame;

        WalletDetails() {

            // Load and resize icons
            ImageIcon walletIcon = new ImageIcon(
                    new ImageIcon("wallet.png").getImage().getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH)
            );
            ImageIcon expenseIcon = new ImageIcon(
                    new ImageIcon("expense.png").getImage().getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH)
            );
            ImageIcon balanceIcon = new ImageIcon(
                    new ImageIcon("balance.png").getImage().getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH)
            );

            frame = new JFrame("IponPadayon");
            frame.setSize(450, 350);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.getContentPane().setBackground(new Color(107, 189, 142));
            frame.setResizable(false);

            ImageIcon logo = new ImageIcon("iponpadayon.png");
            frame.setIconImage(logo.getImage());

            JPanel mainPanel = new JPanel();
            mainPanel.setBounds(20, 20, 400, 210); // Adjusted bounds to leave padding
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(new Color(147, 199, 169));

            // Wallet label at the top-left
            JLabel walletLabel = new JLabel();
            walletLabel.setIcon(walletIcon);
            walletLabel.setText("<html>  Wallet<br>₱ " + String.format("%.2f", wallet) + "</html>");
            walletLabel.setHorizontalTextPosition(JLabel.CENTER);
            walletLabel.setVerticalTextPosition(JLabel.BOTTOM);
            walletLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 25, 15)); // Padding around wallet label

            // Expense label at the top-right
            JLabel expenseLabel = new JLabel();
            expenseLabel.setIcon(expenseIcon);
            expenseLabel.setText("<html>Expenses<br>₱ " + String.format("%.2f", expenses) + "</html>");
            expenseLabel.setHorizontalTextPosition(JLabel.CENTER);
            expenseLabel.setVerticalTextPosition(JLabel.BOTTOM);
            expenseLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 25, 15)); // Padding around expense label

            // Balance label at the bottom-center
            JLabel balanceLabel = new JLabel();
            balanceLabel.setIcon(balanceIcon);
            balanceLabel.setText("<html>Balance<br>₱ " + String.format("%.2f", (wallet - expenses)) + "</html>");
            balanceLabel.setVerticalTextPosition(JLabel.BOTTOM);
            balanceLabel.setHorizontalTextPosition(JLabel.CENTER);
            balanceLabel.setHorizontalAlignment(JLabel.CENTER);
            balanceLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); // Padding around balance label

            // Adding labels to the main panel
            mainPanel.add(walletLabel, BorderLayout.WEST);
            mainPanel.add(expenseLabel, BorderLayout.EAST);
            mainPanel.add(balanceLabel, BorderLayout.SOUTH);

            // Buttons for navigation
            JButton viewExpensesButton = new JButton("View Expenses");
            viewExpensesButton.setFocusable(false);
            viewExpensesButton.setBounds(290, 260, 150, 50);

            JButton backButton = new JButton("Back");
            backButton.setFocusable(false);
            backButton.setBounds(0, 260, 100, 50);
            backButton.setBackground(new Color(29, 51, 38));
            backButton.setForeground(Color.WHITE);

            // Adding components to the frame
            frame.add(mainPanel);
            frame.add(viewExpensesButton);
            frame.add(backButton);

            viewExpensesButton.addActionListener(e -> {
                frame.dispose();
                new ExpenseList();
            });

            backButton.addActionListener(e -> {
                frame.dispose();
                new MainMenu();
            });

            // Display the frame
            frame.setVisible(true);
        }
    }

    static class ExpenseList {

        JFrame frame;

        ExpenseList() {
            frame = new JFrame("IponPadayon");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().setBackground(new Color(107, 189, 142));
            frame.setResizable(false);

            ImageIcon logo = new ImageIcon("iponpadayon.png");
            frame.setIconImage(logo.getImage());

            // Top label for "Expense List"
            JLabel titleLabel = new JLabel("Expense List");
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            frame.add(titleLabel, BorderLayout.NORTH);

            // Expense list panel
            JPanel expensePanel = new JPanel();
            expensePanel.setLayout(new BoxLayout(expensePanel, BoxLayout.Y_AXIS));
            expensePanel.setBackground(new Color(107, 189, 142));
            JScrollPane scrollPane = new JScrollPane(expensePanel);
            scrollPane.setBackground(new Color(107, 189, 142));
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(scrollPane, BorderLayout.CENTER);

            // Populate expense items
            for (String expense : expenseList) {
                JPanel expenseItemPanel = new JPanel();
                expenseItemPanel.setLayout(new BorderLayout());
                expenseItemPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                expenseItemPanel.setMaximumSize(new Dimension(380, 60));
                expenseItemPanel.setBackground(new Color(147, 199, 169));

                String[] parts = expense.split(" - \\$");
                String description = parts[0];
                String amount = parts[1];

                JLabel expenseLabel = new JLabel(description + " - $" + amount);
                expenseLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                expenseItemPanel.add(expenseLabel, BorderLayout.CENTER);

                // Edit and Delete Buttons with Square Size
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
                buttonPanel.setBackground(new Color(147, 199, 169));

                ImageIcon editIcon = new ImageIcon(
                        new ImageIcon("edit.png").getImage().getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)
                );
                JButton editButton = new JButton(editIcon);
                editButton.setToolTipText("Edit");
                editButton.setFocusable(false);
                editButton.setPreferredSize(new Dimension(40, 40)); // Square button
                editButton.setBackground(new Color(147, 199, 169));

                ImageIcon deleteIcon = new ImageIcon(
                        new ImageIcon("delete.png").getImage().getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)
                );
                JButton deleteButton = new JButton(deleteIcon);
                deleteButton.setToolTipText("Delete");
                deleteButton.setFocusable(false);
                deleteButton.setPreferredSize(new Dimension(40, 40)); // Square button
                deleteButton.setBackground(new Color(147, 199, 169));

                buttonPanel.add(editButton);
                buttonPanel.add(deleteButton);

                expenseItemPanel.add(buttonPanel, BorderLayout.EAST);

                expensePanel.add(expenseItemPanel);

                // Button Action Listeners
                editButton.addActionListener(e -> {
                    String newDescription = JOptionPane.showInputDialog("Edit description:", description);
                    if (newDescription == null) {
                        return;
                    }
                
                    String newAmountStr = JOptionPane.showInputDialog("Edit amount:", amount);
                    if (newAmountStr == null) {
                        return;
                    }
                
                    try {
                        double newAmount = Double.parseDouble(newAmountStr);
                        newAmount = Math.round(newAmount * 100.0) / 100.0; // Rounding to two decimal places
                
                        double oldAmount = Double.parseDouble(amount);
                        expenses = expenses - oldAmount + newAmount;
                
                        // Correct small floating-point errors
                        if (Math.abs(expenses) < 0.01) {
                            expenses = 0.0;
                        }
                
                        int index = expenseList.indexOf(expense);
                        String updatedExpense = newDescription + " - $" + String.format("%.2f", newAmount);
                        expenseList.set(index, updatedExpense);
                
                        expenseLabel.setText(updatedExpense);
                
                        saveData();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
                    }
                });                

                deleteButton.addActionListener(e -> {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this expense?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        double amountValue = Double.parseDouble(amount);
                        expenses -= amountValue;
                
                        // Correct small floating-point errors
                        if (Math.abs(expenses) < 0.01) {
                            expenses = 0.0;
                        }
                
                        expenseList.remove(expense);
                        expensePanel.remove(expenseItemPanel);
                        expensePanel.revalidate();
                        expensePanel.repaint();
                        saveData();
                    }
                });
                
            }

            // Back button
            JButton backButton = new JButton("Back");
            backButton.setFocusable(false);
            backButton.setBackground(new Color(29, 51, 38));
            backButton.setForeground(Color.WHITE);
            backButton.addActionListener(e -> {
                frame.dispose();
                new WalletDetails();
            });

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            bottomPanel.setBackground(new Color(107, 189, 142));
            bottomPanel.add(backButton);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        }
    }

    // Save data to file
    private static void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))) {
            writer.println(wallet);
            writer.println(expenses);
            for (String expense : expenseList) {
                writer.println(expense);
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data from file
    private static void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            wallet = Double.parseDouble(reader.readLine());
            expenses = Double.parseDouble(reader.readLine());
            String line;
            while ((line = reader.readLine()) != null) {
                expenseList.add(line);
            }
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }
}
