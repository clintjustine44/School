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

   public void updateStock(int amount) throws InvalidQuantityException {
   }

   public void removeStock(int amount) throws InsufficientStockException, InvalidQuantityException {

   }

   public void displayProduct() {
      System.out.println("Product ID: " + productId + "\nProduct Name: " + productName + "\nQuantity: " + quantity
            + "\nPrice: " + price);
   }

}

class InsufficientStockException extends Exception {
   InsufficientStockException(String message) {
      super(message);
   }
}

class InvalidQuantityException extends Exception {
   InvalidQuantityException(String message) {
      super(message);
   }
}

class PerishableProduct extends Product {
   private String expirationDate;

   PerishableProduct(String productId, String productName, int quantity, double price, String expirationDate) {
      super(productId, productName, quantity, price);
      this.expirationDate = expirationDate;
   }

   @Override
   public void updateStock(int amount) throws InvalidQuantityException {
      if (getQuantity() + amount > 100)
         throw new InvalidQuantityException("Cannot exceed maximum quantity of 100 for perishable products.");
      else if (amount < 0)
         throw new InvalidQuantityException("Quantity cannot be negative.");
      else {
         setQuantity(getQuantity() + amount);
         System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
      }
   }

   @Override
   public void removeStock(int amount) throws InsufficientStockException, InvalidQuantityException {
      if (getQuantity() - amount < 0)
         throw new InsufficientStockException("Insufficient stock for product " + getProductName() + ".");
      else if (amount < 0)
         throw new InvalidQuantityException("Quantity cannot be negative.");
      else {
         setQuantity(getQuantity() + amount);
         System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
      }
   }

   @Override
   public void displayProduct() {
      super.displayProduct();
      System.out.println("\nExpiration Date: " + expirationDate);
   }

}

class NonPerishableProduct extends Product {
   private int shelfLife;

   NonPerishableProduct(String productId, String productName, int quantity, double price, int shelfLife) {
      super(productId, productName, quantity, price);
      this.shelfLife = shelfLife;
   }

   @Override
   public void updateStock(int amount) throws InvalidQuantityException {
      if (getQuantity() + amount > 100)
         throw new InvalidQuantityException("Cannot exceed maximum quantity of 100 for perishable products.");
      else if (amount < 0)
         throw new InvalidQuantityException("Quantity cannot be negative.");
      else {
         setQuantity(getQuantity() + amount);
         System.out.println("Stock updated: " + getProductName() + " new quantity is " + getQuantity());
      }
   }

   @Override
   public void removeStock(int amount) throws InsufficientStockException, InvalidQuantityException {
      if (getQuantity() - amount < 0)
         throw new InsufficientStockException("Insufficient stock for product " + getProductName() + ".");
      else if (amount < 0)
         throw new InvalidQuantityException("Quantity cannot be negative.");
      else
         setQuantity(getQuantity() + amount);
   }

   @Override
   public void displayProduct() {
      super.displayProduct();
      System.out.println("\nShelf Life: " + shelfLife);
   }

}

abstract class InventoryOperation {
   abstract void addProduct(Product product);

   abstract void removeProduct(String productId) throws InsufficientStockException;
}

class Inventory extends InventoryOperation {
   List<Product> products;

   public Inventory() {
      products = new ArrayList<Product>();
   }

   @Override
   public void addProduct(Product product) {
      products.add(product);
      System.out.println("Product added: " + product.getProductName() + " with quantity " + product.getQuantity());
   }

   @Override
   public void removeProduct(String productId) throws InsufficientStockException {

      for (Product product : products) {
         if (productId == product.getProductId()) {
            System.out.println("Product " + product.getProductId() + " removed successfully.");
            products.remove(product);
         } else {
            throw new InsufficientStockException("");
         }
      }
   }

   public void displayInventory() {
      for (Product product : products) {
         product.displayProduct();
      }
   }
}