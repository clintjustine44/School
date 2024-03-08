import java.util.Scanner;

public class RepeatedlyEchoAWord {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a word:");
        String input = scan.nextLine();

        int times = input.length();
        int count = 1;

        while(count <= times){
            System.out.printf("\n" + input);
            count++;
        }

    }
}
