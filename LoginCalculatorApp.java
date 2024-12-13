import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginCalculatorApp {
    private static final String USERNAME = "clintjustine44";
    private static final String PASSWORD = "admin456";

    public static void main(String[] args) {
        // Create the main frame for the application
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);

        // Login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.setBackground(Color.DARK_GRAY);

        // Username and password fields
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        JTextField userField = new JTextField();
        userField.setBackground(Color.darkGray);
        userField.setForeground(Color.WHITE);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        JPasswordField passField = new JPasswordField();
        passField.setBackground(Color.darkGray);
        passField.setForeground(Color.WHITE);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.ORANGE);
        loginButton.setForeground(Color.WHITE);

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        frame.add(loginPanel);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Close login window
                    openCalculator(); // Open the calculator
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect username or password.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to open the calculator
    private static void openCalculator() {
        JFrame calcFrame = new JFrame("Calculator");
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcFrame.setSize(400, 500);
        calcFrame.setLocationRelativeTo(null);


        // Calculator panel
        JPanel calcPanel = new JPanel();
        calcPanel.setLayout(new BorderLayout());
        calcPanel.setBackground(Color.gray);


        // Display area for calculator
        JTextField display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBackground(Color.DARK_GRAY);
        display.setForeground(Color.WHITE);
        calcPanel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5)); // Adjusted for 5 rows
        buttonPanel.setBackground(Color.DARK_GRAY);


        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE"
        };


        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);

            if ("C".equals(text) || "CE".equals(text)) {
                button.setPreferredSize(new Dimension(200, 50));
                button.setBackground(Color.ORANGE);
                button.setForeground(Color.WHITE);
                buttonPanel.add(button);
            } else if ("/+*-=".contains(text)) {
                // Operators: orange background, white text
                button.setBackground(Color.ORANGE);
                button.setForeground(Color.WHITE);
            } else {
                // Other buttons: grey background, white text
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.WHITE);
            }
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if ("0123456789.".contains(command)) {
                        display.setText(display.getText() + command);
                    } else if ("C".equals(command)) {
                        display.setText("");
                    } else if ("CE".equals(command)) {
                        String text = display.getText();
                        if (text.length() > 0) {
                            display.setText(text.substring(0, text.length() - 1));
                        }
                    } else if ("=".equals(command)) {
                        try {
                            display.setText(evaluateExpression(display.getText()));
                        } catch (Exception ex) {
                            display.setText("Error");
                        }
                    } else {
                        display.setText(display.getText() + command);
                    }
                }
            });
        }

        calcPanel.add(buttonPanel, BorderLayout.CENTER);
        calcFrame.add(calcPanel);
        calcFrame.setVisible(true);
    }


    // Method to evaluate the mathematical expression

    private static String evaluateExpression(String expression) {
        try {
            return String.valueOf(evaluatePostfix(infixToPostfix(expression)));
        } catch (Exception e) {
            return "Error";
        }
    }


    // Convert infix expression to postfix
    private static String infixToPostfix(String expression) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                output.append(c);
            } else {
                output.append(' '); // Add space before operator
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    output.append (stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            output.append(' ').append(stack.pop());
        }
        return output.toString().trim();
    }


    // Evaluate the postfix expression

    private static double evaluatePostfix(String expression) {
        Stack<Double> stack = new Stack<>();

        for (String token : expression.split(" ")) {
            if (token.isEmpty()) continue;
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token.charAt(0)) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        if (b == 0) throw new ArithmeticException("Division by zero");
                        stack.push(a / b);
                        break;
                }
            }
        }
        return stack.pop();
    }


    // Determine the precedence of operators

    private static int precedence(char operator) {
        if(operator == '*' || operator == '/')
            return 2;
        else if(operator == '+' || operator == '-')
            return 1;
        else
            return 0;
    }
}
