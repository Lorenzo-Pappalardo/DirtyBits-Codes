package Adapter_Units_Conversion;

/** Adapter */
public class CarAdapter implements ICar {
    private CarAdaptee car;

    @Override
    public Double getSpeed() {
        if (this.car == null)
            this.car = new CarAdaptee();        //Lazy Initialization
        return convert(car.getSpeed());
    }

    /**
     * Converts from mph to kmh
     * @param speed The speed in mph
     * @return Double : Speed in kmh
     */
    private Double convert(double speed) {
        return speed * 1.60934;
    }
}