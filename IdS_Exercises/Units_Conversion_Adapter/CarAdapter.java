package Units_Conversion_Adapter;

/** Adapter class which adapts the Adaptee class */
public class CarAdapter implements ICar {
    private CarAdaptee car;

    @Override
    public double getSpeed() {
        if (this.car == null)
            this.car = new CarAdaptee();
        return convert(car.getSpeed());
    }

    private double convert(double speed) {
        return speed * 1.60934;
    }
}