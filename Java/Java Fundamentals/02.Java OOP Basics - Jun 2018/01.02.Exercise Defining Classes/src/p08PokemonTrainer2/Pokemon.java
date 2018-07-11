package p08PokemonTrainer2;

class Pokemon {
    private String Name;
    private String Element;
    private Integer Health;

    Pokemon(String name, String element, Integer health) {
        Name = name;
        Element = element;
        Health = health;
    }

    String getElement() {
        return Element;
    }

    Integer getHealth() {
        return Health;
    }

    void setHealth(Integer health) {
        Health = health;
    }
}
