package Bridge_Vehicles_Gearshifts;

public class Main {
    public static void main(String[] args) {
        Shift shift = new ManualShift();
        Vehicle vehicle = new Car(shift);
        vehicle.shiftType();

        shift = new AutomaticShift();
        vehicle = new Camion(shift);
        vehicle.shiftType();
    }
}