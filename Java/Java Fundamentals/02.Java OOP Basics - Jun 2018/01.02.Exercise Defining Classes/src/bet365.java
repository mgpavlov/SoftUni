import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bet365 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double defaultK = Double.parseDouble(reader.readLine());

        String[]input = reader.readLine().split(" ");
        double k1 = Double.parseDouble(input[0]);
        double k2 = Double.parseDouble(input[1]);
        double k3 = Double.parseDouble(input[2]);
        double k4 = Double.parseDouble(input[3]);



        double d1 = defaultK/k1; //0.2333333333333333
        double d2 = defaultK/k2; // 0.388888888888889
        double d3 = defaultK/k3; // 0.0853658536585366
        double d4 = defaultK/k4; // 0.1521739130434783

        double difference = d1 + d2 + d3 + d4;

        double averageK =defaultK/difference;


        System.out.println(averageK);

    }
}
