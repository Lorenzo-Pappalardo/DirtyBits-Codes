package Decorator;

public class Hamburger implements IComponent {
    @Override
    public String getProductName() {
        return "Hamburger";
    }

    @Override
    public double getPrice() {
        return 1;
    }
}