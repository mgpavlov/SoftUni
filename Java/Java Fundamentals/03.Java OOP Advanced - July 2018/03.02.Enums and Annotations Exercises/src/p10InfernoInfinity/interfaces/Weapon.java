package p10InfernoInfinity.interfaces;

import p10InfernoInfinity.annotations.WeaponInformation;

@WeaponInformation(author = "Pesho", revision = 3, description = "Used for Java OOP Advanced course - Enumerations and Annotations.", reviewers = { "Pesho", "Svetlio" })
public interface Weapon extends Comparable<Weapon>{
    String getName();

    int getMinDMG();

    int getMaxDMG();

    int getNumberOfSockets();

    double getItemLevel();

    void addGem(Gem gem, int index);

    void removeGem(int index);

    String print();
}
