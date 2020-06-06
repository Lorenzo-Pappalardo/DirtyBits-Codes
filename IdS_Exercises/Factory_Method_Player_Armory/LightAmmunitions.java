package Factory_Method_Player_Armory;

public class LightAmmunitions implements Ammunitions {
    @Override
    public String getName() {
        return "Light Ammunitions";
    }

    @Override
    public float getMultiplier() {
        return 1.0f;
    }
}