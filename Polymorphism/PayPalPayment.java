public class PayPalPayment extends Payment{
        private String email;

        public String getEmail(){
            return email;
        }
    
        public void setEmail(String email){
            this.email = email;
        }

        @Override
        public void processPayment(){
            super.processPayment();
            System.out.println(email);
            System.out.println("PayPal Payment transaction complete...");
            System.out.println();
        }
    }
    