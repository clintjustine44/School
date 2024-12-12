import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


class IponPadayonTest {

    private static ArrayList<User> users = new ArrayList<>();  // Stores User objects

    private static User currentUser  = null; // To store the current logged-in user


    public static void main(String[] args) {

        loadUserData();  // Load user data from userdata.txt

        SwingUtilities.invokeLater(LoginScreen::new); // Show login screen first

    }


    // User class to hold user data

    static class User {

        String username;

        String password;

        double wallet;

        double expenses;

        ArrayList<String> expenseList;


        User(String username, String password) {

            this.username = username;

            this.password = password;

            this.wallet = 0;

            this.expenses = 0;

            this.expenseList = new ArrayList<>();

        }

    }


    // Load user data from userdata.txt

    private static void loadUserData() {

        try (BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] user = line.split(":");

                if (user.length == 2) {

                    users.add(new User(user[0], user[1]));  // Store each user as a User object

                }

            }

        } catch (IOException e) {

            System.out.println("No previous user data found. Starting fresh.");

        }

    }


    // Save user data to userdata.txt

    private static void saveUserData() {

        try (PrintWriter writer = new PrintWriter(new FileWriter("userdata.txt"))) {

            for (User  user : users) {

                writer.println(user.username + ":" + user.password);  // Save username and password separated by ":"

            }

        } catch (IOException e) {

            System.out.println("Error saving user data: " + e.getMessage());

        }

    }


    // Load data for a specific user (wallet and expenses)

    private static void loadData() {

        try (BufferedReader reader = new BufferedReader(new FileReader(currentUser .username + "_data.txt"))) {

            currentUser .wallet = Double.parseDouble(reader.readLine());

            currentUser .expenses = Double.parseDouble(reader.readLine());

            String line;

            while ((line = reader.readLine()) != null) {

                currentUser .expenseList.add(line);

            }

        } catch (IOException e) {

            System.out.println("No data found for user: " + currentUser .username + ". Starting fresh.");

        }

    }


    // Save data for a specific user (wallet and expenses)

    private static void saveData() {

        try (PrintWriter writer = new PrintWriter(new FileWriter(currentUser .username + "_data.txt"))) {

            writer.println(currentUser .wallet);

            writer.println(currentUser .expenses);

            for (String expense : currentUser .expenseList) {

                writer.println(expense);

            }

        } catch (IOException e) {

            System.out.println("Error saving data: " + e.getMessage());

        }

    }


    // Login Screen Class

    static class LoginScreen {

        LoginScreen() {

            // Login frame

            JFrame frame = new JFrame("IponPadayon");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(450, 350);

            frame.setLocationRelativeTo(null);

            frame.setResizable(false);


            ImageIcon logo = new ImageIcon("iponpadayon.png");

            frame.setIconImage(logo.getImage());


            JPanel loginPanel = new JPanel(null);

            loginPanel.setBackground(new Color(107, 189, 142));


            Image resizedImage = logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            ImageIcon resizedLogo = new ImageIcon(resizedImage);


            // Title Label

            JLabel titleLabel = new JLabel("IponPadayon", resizedLogo, SwingConstants.CENTER);

            titleLabel.setBounds(25, 25, 360, 50);

            titleLabel.setForeground(Color.WHITE);

            titleLabel.setFont(new Font("Arial", Font .BOLD, 25));

            titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align text and icon together

            titleLabel.setHorizontalTextPosition(SwingConstants.RIGHT); // Text to the right of the icon

            titleLabel.setVerticalTextPosition(SwingConstants.CENTER); // Center the text vertically

            titleLabel.setIconTextGap(10); // Add spacing between the icon and text


            JLabel userLabel = new JLabel("Username:");

            userLabel.setBounds(95, 80, 200, 25);

            userLabel.setForeground(Color.WHITE);


            JTextField userField = new JTextField();

            userField.setBounds(95, 110, 233, 30);


            JLabel passLabel = new JLabel("Password:");

            passLabel.setBounds(95, 150, 200, 25);

            passLabel.setForeground(Color.WHITE);


            JPasswordField passField = new JPasswordField();

            passField.setBounds(95, 180, 233, 30);


            // Eye icon to toggle password visibility

            ImageIcon eyeIcon = new ImageIcon(new ImageIcon("eye_icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

            ImageIcon eyeIconClosed = new ImageIcon(new ImageIcon("eye_closed_icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));


            // Eye button for toggling visibility

            JButton eyeButton = new JButton(eyeIcon);

            eyeButton.setBounds(330, 180, 30, 30);

            eyeButton.setBackground(new Color(107, 189, 142));

            eyeButton.setBorder(BorderFactory.createEmptyBorder());

            eyeButton.setFocusPainted(false);


            // Toggle password visibility

            eyeButton.addActionListener(e -> {

                if (passField.getEchoChar() == '•') {

                    passField.setEchoChar((char) 0); // Show password

                    eyeButton.setIcon(eyeIconClosed);

                } else {

                    passField.setEchoChar('•'); // Hide password

                    eyeButton.setIcon(eyeIcon);

                }

            });


            JButton loginButton = new JButton("Login");

            loginButton.setBounds(95, 230, 100, 30);

            loginButton.setBackground(new Color(64, 237, 139));

            loginButton.setForeground(Color.WHITE);


            JButton signUpButton = new JButton("Sign Up");

            signUpButton.setBounds(230, 230, 100, 30);

            signUpButton.setBackground(new Color(64, 237, 139));

            signUpButton.setForeground(Color.WHITE);


            loginPanel.add(titleLabel);

            loginPanel.add(userLabel);

            loginPanel.add(userField);

            loginPanel.add(passLabel);

            loginPanel.add(passField);

            loginPanel.add(eyeButton);

            loginPanel.add(loginButton);

            loginPanel.add(signUpButton);


            frame.add(loginPanel);

            frame.setVisible(true);


            // Login button functionality

            loginButton.addActionListener(e -> {

                String username = userField.getText();

                String password = new String(passField.getPassword());


                boolean isAuthenticated = false;

                for (User  user : users) {

                    if (user.username.equals(username) && user.password.equals(password)) {

                        isAuthenticated = true;

                        currentUser  = user; // Set the current user

                        loadData(); // Load user's specific data

                        break;

                    }

                }


                if (isAuthenticated) {

                    JOptionPane.showMessageDialog(frame, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    frame.dispose();

                    SwingUtilities.invokeLater(MainMenu::new); // Open the main menu

                } else {

                    JOptionPane.showMessageDialog(frame, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);

                }

            });


            // Sign Up button functionality

            signUpButton.addActionListener(e -> {

                frame.dispose(); // Close login frame

                openRegisterScreen(); // Open registration panel

            });

        }


        private void openRegisterScreen() {

            // Create the register frame

            JFrame registerFrame = new JFrame("Sign Up");

            registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            registerFrame.setSize(450, 350);

            registerFrame.setLocationRelativeTo(null);

            registerFrame.setResizable(false);


            ImageIcon logo = new ImageIcon("iponpadayon.png");

            registerFrame.setIconImage(logo.getImage());


            JPanel registerPanel = new JPanel(null);

            registerPanel.setBackground(new Color(107, 189, 142));


            JLabel titleLabel = new JLabel("REGISTER", SwingConstants.CENTER);

            titleLabel.setBounds(100, 20, 250, 30);

            titleLabel.setFont(new Font("Arial ", Font.BOLD, 20));

            titleLabel.setForeground(Color.WHITE);


            JLabel registerUsernameLabel = new JLabel("Username:");

            registerUsernameLabel.setBounds(95, 80, 200, 25);

            registerUsernameLabel.setForeground(Color.WHITE);


            JTextField registerUsernameField = new JTextField();

            registerUsernameField.setBounds(95, 110, 233, 30);


            JLabel registerPasswordLabel = new JLabel("Password:");

            registerPasswordLabel.setBounds(95, 150, 200, 25);

            registerPasswordLabel.setForeground(Color.WHITE);


            JPasswordField registerPasswordField = new JPasswordField();

            registerPasswordField.setBounds(95, 180, 233, 30);


            // Eye icon for password visibility toggle

            ImageIcon eyeIcon = new ImageIcon(new ImageIcon("eye_icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

            ImageIcon eyeIconClosed = new ImageIcon(new ImageIcon("eye_closed_icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));


            JButton passwordEyeButton = new JButton(eyeIcon);

            passwordEyeButton.setBounds(335, 185, 20, 20);

            passwordEyeButton.setBackground(new Color(107, 189, 142));

            passwordEyeButton.setBorder(BorderFactory.createEmptyBorder());

            passwordEyeButton.setFocusPainted(false);

            registerPanel.add(passwordEyeButton);


            // Toggle password visibility for password field

            passwordEyeButton.addActionListener(e -> {

                if (registerPasswordField.getEchoChar() == (char) 0) {

                    registerPasswordField.setEchoChar('*');

                    passwordEyeButton.setIcon(eyeIcon);

                } else {

                    registerPasswordField.setEchoChar((char) 0);

                    passwordEyeButton.setIcon(eyeIconClosed);

                }

            });


            JButton registerButton = new JButton("Register");

            registerButton.setBounds(160, 230, 100, 30);

            registerButton.setBackground(new Color(64, 237, 139));

            registerButton.setForeground(Color.WHITE);


            JButton backButton = new JButton("Back");

            backButton.setBounds(0, 281, 100, 30);

            backButton.setBackground(new Color(64, 237, 139));

            backButton.setForeground(Color.WHITE);


            registerPanel.add(titleLabel);

            registerPanel.add(registerUsernameLabel);

            registerPanel.add(registerUsernameField);

            registerPanel.add(registerPasswordLabel);

            registerPanel.add(registerPasswordField);

            registerPanel.add(registerButton);

            registerPanel.add(backButton);


            registerFrame.add(registerPanel);

            registerFrame.setVisible(true);


            // Back button functionality

            backButton.addActionListener(e -> {

                registerFrame.dispose(); // Close registration frame

                SwingUtilities.invokeLater(LoginScreen::new); // Re-open login screen

            });


            // Register button functionality

            registerButton.addActionListener(e -> {

                String newUsername = registerUsernameField.getText().trim();

                String newPassword = new String(registerPasswordField.getPassword()).trim();


                if (newUsername.isEmpty() || newPassword.isEmpty()) {

                    JOptionPane.showMessageDialog(registerFrame, "Both username and password are required.", "Error", JOptionPane.ERROR_MESSAGE);

                    return;

                }


                boolean usernameExists = false;

                for (User  user : users) {

                    if (user.username.equals(newUsername)) {

                        usernameExists = true;

                        break;

                    }

                }


                if (usernameExists) {

                    JOptionPane.showMessageDialog(registerFrame, "Username already exists. Try a different one.", "Error", JOptionPane.ERROR_MESSAGE);

                } else {

                    User newUser  = new User(newUsername, newPassword);

                    users.add(newUser );

                    saveUserData();

                    JOptionPane.showMessageDialog(registerFrame, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    registerFrame.dispose();

                    SwingUtilities.invokeLater(LoginScreen::new); // Re-open login screen

                }

            });

        }

    }


    // Actions Class

    static class Actions {

        JFrame frame;


        Actions() {

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

            updateMoneyButton.setBounds(125, 59, 200, 60);

            updateMoneyButton.setBackground(new Color(64, 237, 139));

            updateMoneyButton.setFocusable(false);


            JButton addExpenseButton = new JButton("+ Add Expense");

            addExpenseButton.setBounds(125, 180, 200, 60);

            addExpenseButton.setBackground(new Color(64, 164, 237));

            addExpenseButton.setFocusable(false);


            JButton backButton = new JButton("Back");

            backButton.setFocusable(false);

            backButton.setBounds(5, 280, 75, 25);

            backButton.setBackground(new Color(29, 51, 38));

            backButton.setForeground(Color.WHITE);


            frame.add(updateMoneyButton);

            frame.add(addExpenseButton);

            frame.add(backButton);


            // Action listeners for the buttons

            updateMoneyButton.addActionListener(e -> {

                String input = JOptionPane.showInputDialog("Enter new wallet balance:");

                if (input == null) {

                    return;

                }

                try {

                    currentUser .wallet = parseAmount(input); // Parse and round the input

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

                    currentUser .expenses += amount;

                    currentUser .expenseList.add(description + " - $" + String.format("%.2f", amount)); // Display with two decimals

                    currentUser .expenses = Math.round(currentUser .expenses * 100.0) / 100.0;

                    saveData();

                    JOptionPane.showMessageDialog(frame, "Expense added successfully!");

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");

                }

            });


            backButton.addActionListener(e -> {

                frame.dispose();

                new MainMenu();

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


    static class MainMenu {

        JFrame frame;


        MainMenu() {

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

            ImageIcon settingsIcon = new ImageIcon(

                    new ImageIcon("settings.png").getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)

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

            mainPanel.setBounds(20, 35, 400, 210); // Adjusted bounds to leave padding

            mainPanel.setLayout(new BorderLayout());

            mainPanel.setBackground(new Color(147, 199, 169));


            // Wallet label at the top-left

            JLabel walletLabel = new JLabel();

            walletLabel.setIcon(walletIcon);

            walletLabel.setText("<html>  Wallet<br>₱ " + String.format("%.2f", currentUser .wallet) + "</html>");

            walletLabel.setHorizontalTextPosition(JLabel.CENTER);

            walletLabel.setVerticalTextPosition(JLabel.BOTTOM);

            walletLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 25, 15)); // Padding around wallet label


            // Expense label at the top-right

            JLabel expenseLabel = new JLabel();

            expenseLabel.setIcon(expenseIcon);

            expenseLabel.setText("<html>Expenses<br>₱ " + String.format("%.2f", currentUser .expenses) + "</html>");

            expenseLabel.setHorizontalTextPosition(JLabel.CENTER);

            expenseLabel.setVerticalTextPosition(JLabel.BOTTOM);

            expenseLabel.setBorder(BorderFactory.createEmptyBorder(20, 15, 25, 15)); // Padding around expense label


            // Balance label at the bottom-center

            JLabel balanceLabel = new JLabel();

            balanceLabel.setIcon(balanceIcon);

            balanceLabel.setText("<html>Balance<br>₱ " + String.format("%.2f", (currentUser .wallet - currentUser .expenses)) + "</html>");

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


            JButton actionButton = new JButton("Actions");

            actionButton.setFocusable(false);

            actionButton.setBounds(0, 260, 100, 50);

            actionButton.setBackground(new Color(29, 51, 38));

            actionButton.setForeground(Color.WHITE);


            // Settings button

            JButton settingsButton = new JButton();

            settingsButton.setIcon(settingsIcon);

            settingsButton.setBounds(390, 0, 50, 30);

            settingsButton.setBackground(new Color(107, 189, 142));

            settingsButton.setFocusable(false);


            // Popup menu for settings

            JPopupMenu settingsMenu = new JPopupMenu();

            JMenuItem changePasswordItem = new JMenuItem("Change Password");

            JMenuItem logoutMenuItem = new JMenuItem("Logout");

            settingsMenu.add(changePasswordItem);

            settingsMenu.add(logoutMenuItem);


            settingsButton.addActionListener(e -> { // Show popup menu below the settings button

                settingsMenu.show(settingsButton, 0, settingsButton.getHeight());

            });


            changePasswordItem.addActionListener(e -> {

                // Open Change Password window

                openChangePasswordWindow();

            });


            logoutMenuItem.addActionListener(e -> {

                JOptionPane.showMessageDialog(frame, "Logout Successful!", "Settings", JOptionPane.INFORMATION_MESSAGE);

                frame.dispose(); // Close main menu

                SwingUtilities.invokeLater(LoginScreen::new); // Re-open login screen

            });


            // Adding components to the frame

            frame.add(mainPanel);

            frame.add(viewExpensesButton);

            frame.add(actionButton);

            frame.add(settingsButton);


            viewExpensesButton.addActionListener(e -> {

                frame.dispose();

                new ExpenseList();

            });


            actionButton.addActionListener(e -> {

                frame.dispose();

                new Actions();

            });


            // Display the frame

            frame.setVisible(true);

        }


        // Method to open Change Password window

        private void openChangePasswordWindow() {

            // Create a new JFrame for the Change Password window

            JFrame changePasswordFrame = new JFrame("Change Password");

            changePasswordFrame.setSize(350, 300);  // Increased size to accommodate the confirm field

            changePasswordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            changePasswordFrame.setLocationRelativeTo(frame); // Position near the main frame

            changePasswordFrame.setResizable(false);


            JPanel panel = new JPanel();

            panel.setLayout(null);

            panel.setBackground(new Color(107, 189, 142));


            // Labels and fields for current password, new password, and confirm password

            JLabel currentPasswordLabel = new JLabel("Current Password:");

            currentPasswordLabel.setBounds(50, 15, 150, 25);

            panel.add(currentPasswordLabel);


            JPasswordField currentPasswordField = new JPasswordField();

            currentPasswordField.setBounds(50, 45, 250, 30);

            panel.add(currentPasswordField);


// Eye icon for current password visibility toggle

ImageIcon eyeIcon = new ImageIcon(new ImageIcon("eye_icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

ImageIcon eyeIconClosed = new ImageIcon(new ImageIcon("eye_closed_icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));


JButton currentPasswordEyeButton = new JButton(eyeIcon);

currentPasswordEyeButton.setBounds(305, 50, 20, 20);

currentPasswordEyeButton.setBackground(new Color(107, 189, 142));

currentPasswordEyeButton.setBorder(BorderFactory.createEmptyBorder());

currentPasswordEyeButton.setFocusPainted(false);

panel.add(currentPasswordEyeButton);


// Toggle password visibility for current password field

currentPasswordEyeButton.addActionListener(e -> {

    if (currentPasswordField.getEchoChar() == (char) 0) {

        currentPasswordField.setEchoChar('*');

        currentPasswordEyeButton.setIcon(eyeIcon);

    } else {

        currentPasswordField.setEchoChar((char) 0);

        currentPasswordEyeButton.setIcon(eyeIconClosed);

    }

});


JLabel newPasswordLabel = new JLabel("New Password:");

newPasswordLabel.setBounds(50, 85, 150, 25);

panel.add(newPasswordLabel);


JPasswordField newPasswordField = new JPasswordField();

newPasswordField.setBounds(50, 115, 250, 30);

panel.add(newPasswordField);


// Eye icon for new password visibility toggle

JButton newPasswordEyeButton = new JButton(eyeIcon);

newPasswordEyeButton.setBounds(305, 120, 20, 20);

newPasswordEyeButton.setBackground(new Color(107, 189, 142));

newPasswordEyeButton.setBorder(BorderFactory.createEmptyBorder());

newPasswordEyeButton.setFocusPainted(false);

panel.add(newPasswordEyeButton);


// Toggle password visibility for new password field

newPasswordEyeButton.addActionListener(e -> {

    if (newPasswordField.getEchoChar() == (char) 0) {

        newPasswordField.setEchoChar('*');

        newPasswordEyeButton.setIcon(eyeIcon);

    } else {

        newPasswordField.setEchoChar((char) 0);

        newPasswordEyeButton.setIcon(eyeIconClosed);

    }

});


JLabel confirmPasswordLabel = new JLabel("Confirm New Password:");

confirmPasswordLabel.setBounds(50, 155, 150, 25);

panel.add(confirmPasswordLabel);


JPasswordField confirmPasswordField = new JPasswordField();

confirmPasswordField.setBounds(50, 185, 250, 30);

panel.add(confirmPasswordField);


// Eye icon for confirm password visibility toggle

JButton confirmPasswordEyeButton = new JButton(eyeIcon);

confirmPasswordEyeButton.setBounds(305, 190, 20, 20);

confirmPasswordEyeButton.setBackground(new Color(107, 189, 142));

confirmPasswordEyeButton.setBorder(BorderFactory.createEmptyBorder());

confirmPasswordEyeButton.setFocusPainted(false);

panel.add(confirmPasswordEyeButton);


// Toggle password visibility for confirm password field

confirmPasswordEyeButton.addActionListener(e -> {

    if (confirmPasswordField.getEchoChar() == (char) 0) {

        confirmPasswordField.setEchoChar('*');

        confirmPasswordEyeButton.setIcon(eyeIcon);

    } else {

        confirmPasswordField.setEchoChar((char) 0);

        confirmPasswordEyeButton.setIcon(eyeIconClosed);

    }

});


JButton changePasswordButton = new JButton("Change Password");

changePasswordButton.setBounds(100, 225, 150, 30);

changePasswordButton.setBackground(new Color(64, 237, 139));

changePasswordButton.setForeground(Color.WHITE);

panel.add(changePasswordButton);


// Action to change password

changePasswordButton.addActionListener(e -> {

    String currentPass = new String(currentPasswordField.getPassword()).trim();

    String newPass = new String(newPasswordField.getPassword()).trim();

    String confirmPass = new String(confirmPasswordField.getPassword()).trim();


    // Check if any field is empty

    if (currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {

        JOptionPane.showMessageDialog(changePasswordFrame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);

        return;

    }


    // Validate if new password and confirm password match

    if (!newPass.equals(confirmPass)) {

        JOptionPane.showMessageDialog(changePasswordFrame, "New password does not match.", "Error", JOptionPane.ERROR_MESSAGE);

        return;

    }


    // Validate current password

    boolean currentPasswordCorrect = false;


    // Update user's password in the list

    for (User  user : users) {

        if (user.username.equals(currentUser .username) && user.password.equals(currentPass)) {

            user.password = newPass;

            currentPasswordCorrect = true;

            break;

        }

    }


    if (!currentPasswordCorrect) {

        JOptionPane.showMessageDialog(changePasswordFrame, "Current password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);

        return;

    }


    // Save updated user data

    saveUserData();


    // Success message and close window

    JOptionPane.showMessageDialog(changePasswordFrame, "Password changed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

    changePasswordFrame.dispose();

});


changePasswordFrame.add(panel);

changePasswordFrame.setVisible(true);

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

        for (String expense : currentUser .expenseList) {

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

                    currentUser .expenses = currentUser .expenses - oldAmount + newAmount;


                    // Correct small floating-point errors

                    if (Math.abs(currentUser .expenses) < 0.01) {

                        currentUser .expenses = 0.0;

                    }


                    int index = currentUser .expenseList.indexOf(expense);

                    String updatedExpense = newDescription + " - $" + String.format("%.2f", newAmount);

                    currentUser .expenseList.set(index, updatedExpense);


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

                    currentUser .expenses -= amountValue;


                    // Correct small floating-point errors

                    if (Math.abs(currentUser .expenses) < 0.01) {

                        currentUser .expenses = 0.0;

                    }


                    currentUser .expenseList.remove(expense);

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

            new MainMenu();

        });


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        bottomPanel.setBackground(new Color(107, 189, 142));

        bottomPanel.add(backButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);


        frame.setVisible(true);

    }

}
}
