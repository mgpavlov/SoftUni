package p08PokemonTrainer;

public class Pokemon {
    private String pokemonName;
    private String pokemonElement;
    private int pokemonHealth;

    public Pokemon(String pokemonName, String pokemonElement, int pokemonHealth) {
        this.pokemonName = pokemonName;
        this.pokemonElement = pokemonElement;
        this.pokemonHealth = pokemonHealth;
    }

    public String getPokemonElement() {
        return this.pokemonElement;
    }


    public int getPokemonHealth() {
        return this.pokemonHealth;
    }

    public void setPokemonHealth() {
        this.pokemonHealth -= 10;
    }
}
