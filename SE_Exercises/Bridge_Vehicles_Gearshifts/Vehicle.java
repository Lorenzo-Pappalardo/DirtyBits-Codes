package Bridge_Vehicles_Gearshifts;

public abstract class Vehicle {
    public Shift shift;

    Vehicle(Shift shift) {
        this.shift = shift;
    }

    public abstract void shiftType();
}