import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p12LittleJohn {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Long> arrows = new LinkedHashMap<>();
        arrows.put(">----->", 0L);
        arrows.put(">>----->", 0L);
        arrows.put(">>>----->>", 0L);

        Pattern pattern = Pattern.compile(">-{5}>|>{2}-{5}>|>{3}-{5}>{2}");

        for (int i = 0; i < 4; i++) {
            Matcher match = pattern.matcher(reader.readLine());

            while (match.find()) {
                if (arrows.containsKey(match.group())) {
                    String arrow = match.group();
                    arrows.put(arrow, arrows.get(arrow) + 1);
                }
            }
        }

        StringBuilder concatenated = new StringBuilder();
        arrows.forEach((key, value) -> concatenated.append(value));

        StringBuilder bin = new StringBuilder(Integer.toBinaryString(Integer.parseInt(concatenated.toString())));
        for (int i = bin.length() - 1; i >= 0; i--) {
            bin.append(bin.charAt(i));
        }

        System.out.println(Integer.parseInt(bin.toString(), 2));
    }
}
