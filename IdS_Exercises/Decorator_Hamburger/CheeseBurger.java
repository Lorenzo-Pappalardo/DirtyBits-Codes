package Decorator;

public class CheeseBurger implements IComponent {
    public String getProductName() {
        return "Cheeseburger";
    }

    @Override
    public double getPrice() {
        return 1.50;
    }
}