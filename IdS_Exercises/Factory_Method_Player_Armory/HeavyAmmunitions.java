package Factory_Method_Player_Armory;

public class HeavyAmmunitions implements Ammunitions {
    @Override
    public String getName() {
        return "Heavy Ammunitions";
    }
    
    @Override
    public float getMultiplier() {
        return 2f;
    }
}