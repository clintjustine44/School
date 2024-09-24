public class Main{
    public static void main(String[] args){
        CreditCardPayment creditCard = new CreditCardPayment("869213", "Clint Justine Nepomuceno", "08/7/2040", "069");
        PayPalPayment paypal = new PayPalPayment();

        creditCard.processPayment();

        paypal.setEmail("clintjustine@gmail.com");
        paypal.processPayment();
    }
}
