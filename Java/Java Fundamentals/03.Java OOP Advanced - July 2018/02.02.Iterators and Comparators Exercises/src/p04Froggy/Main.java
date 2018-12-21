package p04Froggy;
//83/100
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        List<Integer> numbers = Arrays.stream(input.split("[, ]+")).filter(n->!"".equals(n)).map(Integer::parseInt).sorted().collect(Collectors.toList());
        String endCommand = reader.readLine();
        Lake<Integer> lake = new Lake<>(numbers);

        if (!numbers.isEmpty()){
            StringBuilder builder = new StringBuilder();
            for (Integer integer : lake) {
                builder.append(integer + ", ");
            }

            System.out.println(builder.substring(0, builder.length() - 2));
        }
    }
}
