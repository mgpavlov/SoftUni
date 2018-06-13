import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class exam3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String regex = "([!@#$?a-z]+)=(\\d+)--(\\d+)<<([a-z]+)";
        Pattern pattern = Pattern.compile(regex);

        Map<String, Integer> data = new LinkedHashMap<>();

        while (true) {
            String input = reader.readLine();
            if ("Stop!".equals(input)) {
                break;
            }
            if (!input.matches(regex)) {
                continue;
            }

            Matcher matcher = pattern.matcher(input);
            if (matcher.find()){
                String genome = matcher.group(1).replaceAll("[!@#$?]", "");
                int genomeLength = Integer.parseInt(matcher.group(2));
                int countOfGenes = Integer.parseInt(matcher.group(3));
                String organism = matcher.group(4);
                if (genome.length() == genomeLength){
                    data.putIfAbsent(organism, 0);
                    data.put(organism, data.get(organism)+countOfGenes);
                }
            }
        }

        data.entrySet().stream().sorted((a,b)->{
            return b.getValue().compareTo(a.getValue());
        }).forEach(e->{
            System.out.printf("%s has genome size of %d%n", e.getKey(), e.getValue());
        });
    }
}
