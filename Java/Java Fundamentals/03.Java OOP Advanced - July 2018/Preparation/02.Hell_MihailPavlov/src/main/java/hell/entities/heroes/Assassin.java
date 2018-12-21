package hell.entities.heroes;


import hell.interfaces.Inventory;

public class Assassin extends AbstractHero {
    public Assassin(String name, Inventory inventory) {
        //super(name, strength, agility, intelligence, hitPoints, damage);
        super(name, 25, 100, 15, 150, 300, inventory);
    }
}
