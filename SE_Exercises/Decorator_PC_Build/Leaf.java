package Decorator_PC_Build;

import java.util.Random;

public class Leaf implements Component {
    private final String name;
    private final Integer price;

    Leaf(String name) {
        this.name = name;
        Random random = new Random(System.currentTimeMillis());
        price = random.nextInt(500);
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}