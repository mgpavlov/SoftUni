package p10InfernoInfinity.models.weapons;

import p10InfernoInfinity.enums.WeaponType;
import p10InfernoInfinity.interfaces.Weapon;

public class Knife extends BaseWeapon implements Weapon {

    public Knife(String name) {
        super(name, WeaponType.KNIFE.getMinDMG(), WeaponType.KNIFE.getMaxDMG(), WeaponType.KNIFE.getNumberOfSockets());
    }
}
