package Decorator;

public class ExtraBarbecue extends Decorator {
    public ExtraBarbecue(IComponent component) {
        this.component = component;
    }

    @Override
    public String getProductName() {
        return component.getProductName() + "Barbecue Extra";
    }

    @Override
    public double getPrice() {
        return component.getPrice() + 0.20;
    }
}