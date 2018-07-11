package p08PokemonTrainer;//90/100

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Pokemon>> trainerPokemons = new LinkedHashMap<>();
        List<Trainer> trainers = new ArrayList<>();

        while (true){
            String[] input = reader.readLine().split("\\s+");
            if (input[0].equals("Tournament")){
                break;
            }
            String trainerName =input[0];
            String pokemonName =input[1];
            String pokemonElement =input[2];
            int pokemonHealth = Integer.parseInt(input[3]);

            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
            trainerPokemons.putIfAbsent(trainerName, new ArrayList<>());
            trainerPokemons.get(trainerName).add(pokemon);
        }
        trainerPokemons.forEach((key, value) -> {
            Trainer trainer = new Trainer(key, value);
            trainers.add(trainer);
        });
        while (true){
            String element = reader.readLine();
            if (element.equals("End")){
                 break;
            }
            for (Trainer trainer : trainers) {
               if (trainer.getCollectionOfPokemon().stream().filter(p->p.getPokemonElement().equals(element)).collect(Collectors.toList()).size()>0){
                   trainer.setBadges();
               }else {
                   trainer.getCollectionOfPokemon().forEach(Pokemon::setPokemonHealth);
               }
            }
        }

        for (Trainer trainer : trainers) {
            trainer.getCollectionOfPokemon().removeIf(p->p.getPokemonHealth()<=0);
        }
        trainers.stream().sorted((a,b)->{
            return Integer.compare(b.getBadges(), a.getBadges());
        }).forEach(t->{
            System.out.printf("%s %d %d%n", t.getTrainerName(), t.getBadges(), t.getCollectionOfPokemon().size());
        });
    }
}
