package Units_Conversion_Adapter;

public class Client {
    public static void main(String[] args) {
        ICar ferrari = new CarAdapter();
        System.out.println("Speed in kmh: " + ferrari.getSpeed() + " km/h");
    }
}