package Bridge_Vehicles_Gearshifts;

public class Car extends Vehicle {
    Car(Shift shift) {
        super(shift);
    }

    @Override
    public void shiftType() {
        System.out.print("Car with " + shift.getShiftType() + "\n");
    }
}