import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class p06FindAndSumIntegers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        List<String> line = Arrays.asList(input.split("\\s+"));

        OptionalInt sumOfInts = line.stream()
                .filter(x -> !x.isEmpty())
                .filter(x -> {
                    try {
                        Integer.parseInt(x);
                        return true;
                    }catch (Exception ignored){
                        return false;
                    }

                }).mapToInt(Integer::parseInt).reduce((x,y)->x+y);

        if (sumOfInts.isPresent()){
            System.out.println(sumOfInts.getAsInt());
        }else {
            System.out.println("No match");
        }
    }
}
