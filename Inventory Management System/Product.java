import java.util.*;

class Product {
   private String productId;
   private String productName;
   private int quantity;
   private double price;

   public Product(String productId, String productName, int quantity, double price) {
      this.productId = productId;
      this.productName = productName;
      this.quantity = quantity;
      this.price = price;
   }

   public String getProductId() {
      return productId;
   }

   public String getProductName() {
      return productName;
   }

   public int getQuantity() {
      return quantity;
   }

   public double getPrice() {
      return price;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public void updateStock(int amount) throws InvalidQuantityException, InsufficientStockException {
   }

   public void removeProduct(int amount) throws InvalidQuantityException, InsufficientStockException {

   }

}

class PerishableProduct extends Product {
   private String expirationDate;

   public PerishableProduct(String productId, String productName, int quantity, double price, String expirationDate) {
      super(productId, productName, quantity, price);
      this.expirationDate = expirationDate;
   }

   public String getExpirationDate() {
      return expirationDate;
   }

   public void setExpirationDate(String expirationDate) {
      this.expirationDate = expirationDate;
   }

   @Override
   public void updateStock(int amount) throws InvalidQuantityException {
      if (amount < 0)
         throw new InvalidQuantityException("Quantity cannot be negative.");

      else if (getQuantity() + amount >= 100)
         throw new InvalidQuantityException("Cannot exceed maximum quantity of 100 for perishable products.");

      else {
         setQuantity(getQuantity() + amount);
         System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
      }
   }

   @Override
   public void removeProduct(int amount) throws InsufficientStockException {
      if (getQuantity() - amount < 0)
         throw new InsufficientStockException("Insufficient stock for product " + getProductName());

      else {
         setQuantity(getQuantity() - amount);
         System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
      }
   }
}

class NonPerishableProduct extends Product {
   private int shelfLife;

   public NonPerishableProduct(String productId, String productName, int quantity, double price, int shelfLife) {
      super(productId, productName, quantity, price);
      this.shelfLife = shelfLife;
   }

   public int getShellLife() {
      return shelfLife;
   }

   public void setShelfLife(int shelfLife) {
      this.shelfLife = shelfLife;
   }

   @Override
   public void updateStock(int amount) {
      setQuantity(getQuantity() + amount);
   }
}

abstract class InventoryOperation {
   public abstract void addProduct(Product product);

   public abstract void removeProduct(String productId) throws InsufficientStockException;
}

class Inventory extends InventoryOperation {
   private ArrayList<Product> products;

   public Inventory() {
      products = new ArrayList<>();
   }

   @Override
   public void addProduct(Product product) {
      products.add(product);
      System.out.println("Product added: " + product.getProductName() + " with quantity " + product.getQuantity());
   }

   @Override
   public void removeProduct(String productId) throws InsufficientStockException {
      boolean notFound = true;

      for (Product product : products) {
         if (product.getProductId().equals(productId)) {
            System.out.println("Product " + product.getProductId() + " removed successfully.");
            products.remove(product);
            notFound = false;
            return;
         }
      }

      if (notFound)
         throw new InsufficientStockException("Product not found.");
   }

   public void displayInventory() {
      System.out.println("Inventory: ");
      for (Product product : products) {
         System.out.println("Product ID: " + product.getProductId() + "\nProduct Name: " + product.getProductName()
               + "\nQuantity: " + product.getQuantity() + "\nPrice: " + product.getPrice());
      }
   }
}

class InvalidQuantityException extends Exception {
   public InvalidQuantityException(String message) {
      super(message);
   }
}

class InsufficientStockException extends Exception {
   public InsufficientStockException(String message) {
      super(message);
   }
}