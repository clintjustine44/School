import java.util.ArrayList;

public class Library{
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> authors = new ArrayList<>();
    private ArrayList<String> isbns = new ArrayList<>();
    private ArrayList<Integer> availableCopies = new ArrayList<>();
    private ArrayList<Integer> borrowedCopies = new ArrayList<>();

    public Library(){
        initializeBooks();
    }

    public void initializeBooks(){
        titles.add("Java Programming");
        authors.add("C.J. Nepomuceno");
        isbns.add("000001");
        availableCopies.add(5);
        borrowedCopies.add(0);

        titles.add("Unsaon Pagwala sa Stress");
        authors.add("J.C Crist");
        isbns.add("000002");
        availableCopies.add(0);
        borrowedCopies.add(0);

        titles.add("How To Code");
        authors.add("M.C. Edition");
        isbns.add("000003");
        availableCopies.add(2);
        borrowedCopies.add(0);
    }

    private boolean isValidIsbn(String isbn){
        if(isbn.length() != 6 || isbn.charAt(0) == '-'){
            return false;
        }

        for(char c : isbn.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
    
        return true;
    }

    public void createBook(String title, String author, String isbn, int copies){
        if(!isValidIsbn(isbn)){
            System.out.println("Invalid ISBN format. ISBN must be exactly 6 digits and cannot be negative.");
            return;
        }
    
        if(copies < 0){
            System.out.println("Invalid number of copies. Must be zero or greater.");
            return;
        }
    
        if(isbns.contains(isbn)){
            System.out.println("Book with ISBN " + isbn + " already exists in the library.");
        }else{
            titles.add(title);
            authors.add(author);
            isbns.add(isbn);
            availableCopies.add(copies);
            borrowedCopies.add(0);
            System.out.println("Book(s) added to the library.");
        }
    }

    public void borrowBook(String isbn){
        int index = isbns.indexOf(isbn);
        
        if(index == -1){
            System.out.println("Book with ISBN " + isbn + " doesn't exist in the library.");
        }else{
            if(availableCopies.get(index) > 0){
                availableCopies.set(index, availableCopies.get(index) - 1);
                borrowedCopies.set(index, borrowedCopies.get(index) + 1);
                System.out.println("Book borrowed: " + titles.get(index));
            }else{
                System.out.println("Book doesn't have available copies.");
            }
        }
    }

    public void returnBook(String isbn){
        int index = isbns.indexOf(isbn);

        if(index != -1){
            if(borrowedCopies.get(index) > 0){
                availableCopies.set(index, availableCopies.get(index) + 1);
                borrowedCopies.set(index, borrowedCopies.get(index) - 1);
                System.out.println("Book returned: " + titles.get(index));
            }else{
                System.out.println("No copies of " + titles.get(index) + " have been borrowed.");
            }
        }else{
            System.out.println("Book with ISBN " + isbn + " doesn't exist in the library.");
        }
    }

    public void detailsBook(){
        if(titles.isEmpty()){
            System.out.println("There are currently no books in the library.");
        }else{ 
            System.out.println("\nBOOK                           | AUTHOR               | ISBN       | COPIES ");
            System.out.println("-----------------------------------------------------------------------");
            
            for(int i = 0; i < titles.size(); i++){
                String title = titles.get(i);
                String author = authors.get(i);
                String isbn = isbns.get(i);
                int copies = availableCopies.get(i);
                
                String titleFormatted = String.format("%-30s", title);
                String authorFormatted = String.format("%-20s", author);
                String isbnFormatted = String.format("%-10s", isbn);

                System.out.println(titleFormatted + " | " + authorFormatted + " | " + isbnFormatted + " | " + copies);
            }
        }
    }
}
