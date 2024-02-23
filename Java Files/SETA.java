import java.util.Scanner;

public class SETA {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.printf("Enter a number between 0 - 35: ");
        int value = scan.nextInt();

        if(value <= 9)
            System.out.println(" " + value);
        else
            System.out.println((char)(byte) 'A', (value - 10));
        

    }
}
