package Decorator_PC_Build;

public class Main {
    public static void main(String[] args) {
        Component comp1 = new ConcreteComponent("CPU");
        System.out.println(comp1.getName() + " price: " + comp1.getPrice());

        Component comp2 = new Discount(comp1);
        System.out.println(comp2.getName() + " price: " + comp2.getPrice());

        Component comp3 = new ConcreteComponent("Memory");
        System.out.println(comp3.getName() + " price: " + comp3.getPrice());

        Component comp5 = new Discount(new AddRGB(comp3));
        System.out.println(comp5.getName() + " price: " + comp5.getPrice());
    }
}