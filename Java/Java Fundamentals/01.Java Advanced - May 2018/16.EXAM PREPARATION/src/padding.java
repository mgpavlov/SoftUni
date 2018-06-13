import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class padding {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input1 = reader.readLine();
        String input2 = reader.readLine();
        int num = input2.length();
        input1 = String.format("%"+num+"s",input1);
        System.out.println(input1);
    }
}
