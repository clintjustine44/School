import java.util.Scanner;

public class PieEatingContest {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.printf("What is your weight (in pounds)? ");
        int pounds = scan.nextInt();

        if (pounds >= 30 && pounds <= 250)
            System.out.println("You are allowed in the contest!");
        else
            System.out.println("You are not allowed in the contest!");
    }
}
