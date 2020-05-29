package Factory_Method_Game;

public class Pistol implements Weapon {
    Ammunitions ammo;

    Pistol(Ammunitions ammo) {
        this.ammo = ammo;
    }

    @Override
    public String getName() {
        return "Pistol with " + ammo.getName();
    }

    @Override
    public float getAOEDamage() {
        return 10 * ammo.getMultiplier();
    }

    @Override
    public float getSingleTargetDamage() {
        return 50 * ammo.getMultiplier();
    }
}