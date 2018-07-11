package p08PokemonTrainer;

import java.util.List;

public class Trainer {

    private String trainerName;
    private int badges;
    private List<Pokemon> collectionOfPokemon;

    public Trainer(String trainerName, List<Pokemon> collectionOfPokemon) {
        this.trainerName = trainerName;
        this.collectionOfPokemon = collectionOfPokemon;
    }

    public String getTrainerName() {
        return this.trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public int getBadges() {
        return this.badges;
    }

    public void setBadges() {
        this.badges++;
    }

    public List<Pokemon> getCollectionOfPokemon() {
        return this.collectionOfPokemon;
    }

    public void setCollectionOfPokemon(List<Pokemon> collectionOfPokemon) {
        this.collectionOfPokemon = collectionOfPokemon;
    }
}
