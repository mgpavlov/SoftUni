package hell.entities.heroes;


import hell.interfaces.Inventory;

public class Barbarian extends AbstractHero {
    public Barbarian(String name, Inventory inventory) {
        //super(name, strength, agility, intelligence, hitPoints, damage);
        super(name, 90, 25, 10, 350, 150, inventory);
    }
}
