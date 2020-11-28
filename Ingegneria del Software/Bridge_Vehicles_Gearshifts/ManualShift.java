package Bridge_Vehicles_Gearshifts;

public class ManualShift implements Shift {
    @Override
    public String getShiftType() {
        return "Manual Gearshift";
    }
}