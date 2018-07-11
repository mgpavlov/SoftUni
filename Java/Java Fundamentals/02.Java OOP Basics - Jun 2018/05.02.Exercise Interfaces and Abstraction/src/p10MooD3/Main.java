package p10MooD3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String[] arr = reader.readLine().split(" \\| ");

        Able able = null;

        if ("Demon".equals(arr[1])) {
            able = new Demon(arr[0], Integer.parseInt(arr[3]), Double.parseDouble(arr[2]));
        }else {
            able = new Archangel(arr[0], Integer.parseInt(arr[3]), Integer.parseInt(arr[2]));
        }

        System.out.println(able.toString());
    }
}
