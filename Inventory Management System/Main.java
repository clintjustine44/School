public class Main {
    public static void main(String... args) {
        PerishableProduct apple = new PerishableProduct("P001", "Apple", 50, 0.5, "2024-12-31");
        NonPerishableProduct rice = new NonPerishableProduct("NP001", "Rice", 200, 1.0, 365);
        Inventory inventory = new Inventory();

        System.out.println("\nCase 1");
        inventory.addProduct(apple);

        System.out.println("\nCase 2");
        inventory.addProduct(rice);

        System.out.println("\nCase 3");
        try {
            apple.updateStock(30); // Add 30 Apples into the current stock (which is 50).
        } catch (InvalidQuantityException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nCase 4");
        try {
            apple.updateStock(30); // Add 30 Apples into the current stock (which is 80).
        } catch (InvalidQuantityException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nCase 5");
        try {
            inventory.removeProduct("P001");
        } catch (InsufficientStockException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nCase 6");
        try {
            apple.removeProduct(90); // Tries to remove 90 out of 80 Apples.
        } catch (InsufficientStockException e) {
            System.out.println(e.getMessage());
        }

    }
}