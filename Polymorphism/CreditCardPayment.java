public class CreditCardPayment extends Payment{
    private String cardnumber;
    private String cardholdername;
    private String expirationdate;
    private String cvv;

    public CreditCardPayment(String cardnumber, String cardholdername, String expirationdate, String cvv){
        this.cardnumber = cardnumber;
        this.cardholdername = cardholdername;
        this.expirationdate = expirationdate;
        this.cvv = cvv;
    }

    @Override
    public void processPayment(){
        super.processPayment();
        System.out.println("Card Holder: " + cardholdername);
        System.out.println("Car Number: " + cardnumber);
        System.out.println("CVV: " + cvv);
        System.out.println("CreditCard transaction complete...");
        System.out.println();
    }
}