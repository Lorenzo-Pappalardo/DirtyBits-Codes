package Decorator;

public class ExtraCetriolo extends Decorator {
    public ExtraCetriolo(IComponent component)     {
        this.component = component;
    }

    @Override
    public String getProductName() {
        return component.getProductName() + "Cetriolo Extra";
    }

    @Override
    public double getPrice() {
        return component.getPrice() + 0.40;
    }
}