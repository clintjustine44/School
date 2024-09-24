public class Main{
    public static void main(String[] args){
        Refrigerator ref = new Refrigerator();
        WashingMachine washingMachine = new WashingMachine();

        ref.turnOn();
        ref.turnOff();
        
        System.out.println();

        washingMachine.turnOn();
        washingMachine.turnOff();
    }
}