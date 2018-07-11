package p08PokemonTrainer2;

import p08PokemonTrainer2.Trainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    private static final String END_OF_LINES = "Tournament";
    private static final String END_OF_COMMANDS = "End";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Trainer> record = new LinkedHashMap<>();

        String input = reader.readLine();
        while (!END_OF_LINES.equals(input)) {
            String[] tokens = input.split("\\s+");
            String trainerName = tokens[0];
            String pokemonName = tokens[1];
            String pokemonElement = tokens[2];
            Integer pokemonHealth = Integer.parseInt(tokens[3]);

            Pokemon cauthPokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);

            record.putIfAbsent(trainerName, new Trainer(trainerName, 0));
            record.get(trainerName).addPokemon(cauthPokemon);

            input = reader.readLine();
        }

        input = reader.readLine();
        while (!END_OF_COMMANDS.equals(input)) {
            for (Map.Entry<String, Trainer> kvp : record.entrySet()) {
                kvp.getValue().analize(input);
            }

            input = reader.readLine();
        }

        StringBuilder sb = new StringBuilder();

        record.entrySet().stream()
                .sorted((t1, t2) -> Integer.compare(t2.getValue().getBadges(), t1.getValue().getBadges()))
                .forEach(t -> sb.append(t.getValue()));

        System.out.println(sb);
    }
}