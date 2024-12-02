
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class IponPadayon {

    private static double wallet = 0;
    private static double expenses = 0;
    private static ArrayList<String> expenseList = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        SwingUtilities.invokeLater(MainMenu::new);
    }

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
