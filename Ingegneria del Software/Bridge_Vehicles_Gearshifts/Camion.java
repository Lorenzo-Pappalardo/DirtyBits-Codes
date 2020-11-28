package Bridge_Vehicles_Gearshifts;

public class Camion extends Vehicle {
    Camion(Shift shift) {
        super(shift);
    }

    @Override
    public void shiftType() {
        System.out.print("Camion with " + shift.getShiftType() + "\n");
    }
}