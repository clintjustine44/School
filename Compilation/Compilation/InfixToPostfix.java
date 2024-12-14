import java.util.Stack;

class InfixToPostfix {

    // Precedence function
    static int precedence(char c) {
        if (c == '*' || c == '/')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

    // Evaluate a postfix expression
    static String evaluatePostfix(String s) {
        Stack<Double> stack = new Stack<>();
        double num1, num2;
    
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                stack.push((double) (c - '0'));
            } else {
                num2 = stack.pop();
                num1 = stack.pop();
                switch (c) {
                    case '*':
                        stack.push(num1 * num2);
                        break;
                    case '/':
                        stack.push(num1 / num2);
                        break;
                    case '+':
                        stack.push(num1 + num2);
                        break;
                    case '-':
                        stack.push(num1 - num2);
                        break;
                    default:
                        break;
                }
            }
        }
    
        double result = stack.pop();
    
        return String.format("%.2f", result);
    }
    

    // Convert infix expression to postfix
    static String convertInfix(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Ignore spaces
            if (c == ' ') {
                continue;
            }

            // If the scanned character is a digit or letter, add it to the result
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } 
            // If the scanned character is '(', push it to the stack
            else if (c == '(') {
                stack.push(c);
            } 
            // If the scanned character is ')', pop from the stack until '(' is encountered
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove the matching '('
                }
            } 
            // If an operator is encountered
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop all the remaining operators from the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}