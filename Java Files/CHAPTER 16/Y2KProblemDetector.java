import java.util.Scanner;

public class Y2KProblemDetector {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int birthYear, age, currentYear;

        System.out.println("Year of Birth (XX): ");
        birthYear = scan.nextInt();
        System.out.println("Current year (XX): ");
        currentYear = scan.nextInt();

        if(currentYear < birthYear)
            age = (100 + currentYear) - birthYear;
        else
            age = currentYear - birthYear;

        System.out.println("Your age: " + age);

    }
}
