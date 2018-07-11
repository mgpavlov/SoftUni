package p08PokemonTrainer2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Trainer {
    private static final int LOST_HEALTH = 10;

    private String Name;
    private int Badges;
    private List<Pokemon> Pokemons;

    Trainer(String name, int badges) {
        if (this.Pokemons == null) {
            this.Pokemons = new ArrayList<>();
        }

        Name = name;
        Badges = badges;
    }

    int getBadges() {
        return this.Badges;
    }

    void addPokemon(Pokemon pokemon) {
        Pokemons.add(pokemon);
    }

    void analize(String element) {
        boolean trainerHasSuchPokemon = this.Pokemons.stream()
                .filter(p -> p.getElement().equals(element))
                .collect(Collectors.toList()).size() > 0;

        if (trainerHasSuchPokemon) {
            this.Badges++;
        } else {
            for (Pokemon pokemon : Pokemons) {
                pokemon.setHealth(pokemon.getHealth() - LOST_HEALTH);
            }

            Pokemons = this.Pokemons.stream().filter(p -> p.getHealth() > 0).collect(Collectors.toList());
        }
    }

    @Override
    public String toString() {
        return String.format("%s %d %d%n", this.Name, this.Badges, this.Pokemons.size());
    }
}
