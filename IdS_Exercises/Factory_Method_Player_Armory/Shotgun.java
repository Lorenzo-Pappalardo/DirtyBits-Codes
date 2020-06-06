package Factory_Method_Game;

public class Shotgun implements Weapon {
    Ammunitions ammo;

    Shotgun(Ammunitions ammo) {
        this.ammo = ammo;
    }

    @Override
    public String getName() {
        return "Shotgun with " + ammo.getName();
    }

    @Override
    public float getAOEDamage() {
        return 100 * ammo.getMultiplier();
    }

    @Override
    public float getSingleHitDamage() {
        return 33.3f * ammo.getMultiplier();
    }
}