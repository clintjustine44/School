import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Library lib = new Library();
        String title, author, isbn;
        int copies;
        int choice = 0;

        do{
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.println("Library Management System\n1. Create a book\n2. Borrow a book\n3. Return a book\n4. Details of the book(s)\n5. Exit");
            System.out.println("Select a choice: ");
            choice = scan.nextInt();
            scan.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter the title of the book: ");
                    title = scan.nextLine();
                    System.out.println("Enter the author of the book: ");
                    author = scan.nextLine();
                    System.out.println("Enter the ISBN of the book (######): ");
                    isbn = scan.nextLine();
                    System.out.println("Enter the # of available copies: ");
                    copies = scan.nextInt();
                    lib.createBook(title,author,isbn,copies);
                    break;
                case 2:
                    System.out.println("Enter the ISBN of the book (######): ");
                    isbn = scan.nextLine();
                    lib.borrowBook(isbn);
                    break;
                case 3:
                    System.out.println("Enter the ISBN of the book (######): ");
                    isbn = scan.nextLine();
                    lib.returnBook(isbn);
                    break;
                case 4:
                    lib.detailsBook();
                    break;
                case 5:
                    System.out.println("\nExiting the program.");
                    break;
                default:
                    System.out.println("Invalid input. Please select a choice.");
                    break;
            }
        }while(choice != 5);
    }
}