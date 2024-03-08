import java.util.Scanner;

public class OneLetterPerLine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.printf("Enter a word: ");
        String input = scan.nextLine();

        int length = input.length();
        int count = 0;

        while(count < length){
            System.out.println("" + input.charAt(count));    
            count++;
        }

    }
}
