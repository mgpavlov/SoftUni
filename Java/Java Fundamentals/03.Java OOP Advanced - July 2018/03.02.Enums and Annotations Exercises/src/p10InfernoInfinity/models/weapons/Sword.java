package p10InfernoInfinity.models.weapons;


import p10InfernoInfinity.enums.WeaponType;
import p10InfernoInfinity.interfaces.Weapon;

public class Sword extends BaseWeapon implements Weapon {

    public Sword(String name) {
        super(name, WeaponType.SWORD.getMinDMG(), WeaponType.SWORD.getMaxDMG(), WeaponType.SWORD.getNumberOfSockets());
    }
}
