package p10InfernoInfinity.models.weapons;

import p10InfernoInfinity.enums.WeaponType;
import p10InfernoInfinity.interfaces.Weapon;

public class Axe extends BaseWeapon implements Weapon {

    public Axe(String name) {
        super(name, WeaponType.AXE.getMinDMG(), WeaponType.AXE.getMaxDMG(), WeaponType.AXE.getNumberOfSockets());
    }
}
