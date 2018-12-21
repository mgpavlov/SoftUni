package p01GenericBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(reader.readLine());

        while(input-- > 0){
            String command = reader.readLine();
            Box box = new Box(command);
            System.out.println(box.toString());
        }
    }
}
