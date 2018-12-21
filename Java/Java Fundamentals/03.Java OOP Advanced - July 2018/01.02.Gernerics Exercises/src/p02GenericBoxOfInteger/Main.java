package p02GenericBoxOfInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(reader.readLine());

        while(input-- > 0){
            Integer command = Integer.valueOf(reader.readLine());
            Box box = new Box(command);
            System.out.println(box.toString());
        }
    }
}
