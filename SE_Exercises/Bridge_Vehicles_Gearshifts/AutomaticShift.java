package Bridge_Vehicles_Gearshifts;

public class AutomaticShift implements Shift {
    @Override
    public String getShiftType() {
        return "Automatic Gearshift";
    }
}