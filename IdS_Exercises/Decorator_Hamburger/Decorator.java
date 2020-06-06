package Decorator;

public abstract class Decorator implements IComponent {
    protected IComponent component;
    
    @Override
    public abstract String getProductName();

    @Override
    public abstract double getPrice();
}