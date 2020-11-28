package Decorator_PC_Build;

import java.util.Random;

public class Discount extends Decorator {
    Discount(Component component) {
        super(component);
    }

    private Integer discount() {
        System.out.println("Applying a discount...");
        Random random = new Random(System.currentTimeMillis());
        Integer discount = random.nextInt(150);
        Integer finalPrice = component.getPrice() - discount;
        System.out.println("Discount applied: " + discount + " off the original price!");
        if (finalPrice < 0)
            return 0;
        return finalPrice;
    }

    public Integer getPrice() {
        return discount();
    }
}