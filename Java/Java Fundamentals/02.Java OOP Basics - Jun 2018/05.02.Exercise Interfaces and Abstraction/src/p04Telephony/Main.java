package p04Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] calls = reader.readLine().split("\\s+");
        String[] sites = reader.readLine().split("\\s+");
        Phone smartphone = new Phone();
        for (String number : calls) {

            try {
                smartphone.calling(number);
            }catch (IllegalArgumentException exc){
                System.out.println(exc.getMessage());
            }

        }
        for (String URL : sites) {
            try {
                smartphone.browsing(URL);
            }catch (IllegalArgumentException exc){
                System.out.println(exc.getMessage());
            }

        }
    }
}
