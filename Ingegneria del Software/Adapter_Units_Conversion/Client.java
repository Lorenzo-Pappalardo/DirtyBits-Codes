package Adapter_Units_Conversion;

public class Client {
    public static void main(String[] args) {
        CarAdaptee americanFerrari = new CarAdaptee();
        ICar italianFerrari = new CarAdapter();
        System.out.println("American Ferrari, Speed: " + americanFerrari.getSpeed() + " mph");
        System.out.println("Italian Ferrari, Speed: " + italianFerrari.getSpeed() + " kmh");
    }
}