import java.util.Scanner;

public class WordsSeparatedByDots {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter first word:");
        String firstWord = scan.nextLine();

        System.out.println("Enter second word:");
        String secondWord = scan.nextLine();

        int length = firstWord.length() + secondWord.length();
        int times = 30 - length;
        int count = 1;

        System.out.print(firstWord);

        while(count < times){
            System.err.print(".");
            count++;
        }

        System.out.print(secondWord);
    }
}