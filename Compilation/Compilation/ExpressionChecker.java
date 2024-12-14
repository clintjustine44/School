import java.util.Stack;

public class ExpressionChecker {
    
    public static boolean isWellDefined(String expression) {
        Stack<Character> stack = new Stack<>();
        char lastChar = ' ';
        
        for (char ch : expression.toCharArray()) {
            if (isOpeningBracket(ch)) {
                stack.push(ch);
            } else if (isClosingBracket(ch)) {
                if (stack.isEmpty() || !matches(stack.pop(), ch)) {
                    return false; // Unmatched closing bracket
                }
            } else if (isOperator(ch)) {
                if (isOperator(lastChar)) {
                    return false; // Consecutive operators
                }
            } else if (!Character.isDigit(ch) && !Character.isWhitespace(ch)) {
                return false; // Invalid character
            }
            lastChar = ch;
        }
        return stack.isEmpty() && !isOperator(lastChar); // Ensure no unmatched brackets and no trailing operator
    }

    private static boolean isOpeningBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static boolean isClosingBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') || 
               (open == '[' && close == ']') || 
               (open == '{' && close == '}');
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}