import java.io.*;
import java.util.*;

public class CheckExpression {
    public static void main(String[] args) {
        String fileName = "expressions.txt";
        List<String> expressions = readExpressions(fileName);
        for (String expression : expressions) {
            if(isWellDefined(expression))
                System.out.println("Well Defined");
            else
                System.out.println("Not Well Defined");
        }
    }

    public static List<String> readExpressions(String fileName) {
        List<String> expressions = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                expressions.add(line);
            }
        } catch (IOException e) {
            return null;
        }

        return expressions;
    }

    public static boolean isWellDefined(String expression){
        Stack<Character> stack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if(ch == '{' || ch == '(' || ch == '[')
                stack.push(ch);
            else if(ch == '}' || ch == ')' || ch == ']'){
                if(stack.isEmpty())
                    return false;
                char lastOpened = stack.pop();
                if(!matches(lastOpened, ch))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static boolean matches(char open, char close) {
        return (open == '(' && close == ')' ||
                open == '[' && close == ']' ||
                open == '{' && close == '}');
    }
}