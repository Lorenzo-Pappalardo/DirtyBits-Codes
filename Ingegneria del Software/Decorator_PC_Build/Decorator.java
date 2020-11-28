package Decorator_PC_Build;

public class Decorator implements Component {
    Component component;

    Decorator(Component component) {
        this.component = component;
    }

    public String getName() {
        return component.getName();
    }

    public Integer getPrice() {
        return component.getPrice();
    }
}