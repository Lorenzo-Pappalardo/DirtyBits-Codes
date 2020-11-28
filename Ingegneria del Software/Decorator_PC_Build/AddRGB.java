package Decorator_PC_Build;

import java.util.Random;

public class AddRGB extends Decorator {
    AddRGB(Component component) {
        super(component);
    }

    private Integer addRGB() {
        System.out.println("Adding RGBs...");
        Random random = new Random(System.currentTimeMillis());
        Integer extraCost = random.nextInt(150);
        Integer finalPrice = component.getPrice() + extraCost;
        System.out.println("RGBs added: " + extraCost + " added to the original price!");
        return finalPrice;
    }

    public Integer getPrice() {
        return addRGB();
    }
}