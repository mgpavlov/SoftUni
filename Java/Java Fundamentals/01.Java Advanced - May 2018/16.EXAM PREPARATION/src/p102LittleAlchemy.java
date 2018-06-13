import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class p102LittleAlchemy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Deque<Integer> stones = Arrays.stream(reader.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        List<Integer> goldenBites = new ArrayList<>();

        while (true){
            String input = reader.readLine();
            if ("Revision".equals(input)){
                break;
            }
            if (input.startsWith("Apply")){
                int acid = Integer.valueOf(input.split(" ")[2]);
                for (int i = 0; i < acid ; i++) {
                    if (stones.isEmpty()){
                        break;
                    }
                    if (stones.peek()-1 == 0){
                        goldenBites.add(stones.pop());
                    }else{
                        stones.add(stones.pop()-1);
                    }
                }
            }
            if (input.startsWith("Air")){
                if (!goldenBites.isEmpty()){
                    goldenBites.remove(0);
                    stones.add(Integer.parseInt(input.split(" ")[2]));
                }
            }
        }
        stones.forEach(e-> System.out.print(e + " "));
        System.out.println();
        System.out.println(goldenBites.size());
    }
}
