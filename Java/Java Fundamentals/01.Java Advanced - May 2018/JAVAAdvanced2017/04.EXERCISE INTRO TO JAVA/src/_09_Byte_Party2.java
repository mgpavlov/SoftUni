import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _09_Byte_Party2 { // 100/100
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(reader.readLine());
            numbers[i] = number;
        }

        while (true) {
            String[] command = reader.readLine().split("\\s+");
            if (command[0].equals("party")) {
                for (int number : numbers) {
                    System.out.println(number);
                }
                break;
            }

            int action = Integer.parseInt(command[0]);
            int position = Integer.parseInt(command[1]);

            switch (action) {
                case -1:
                    for (int i = 0; i < numbers.length; i++) {
                        numbers[i] = (numbers[i] ^ (1 << position));
                    }
                    break;
                case 0:
                    for (int i = 0; i < numbers.length; i++) {
                        numbers[i] = numbers[i] & ~(1 << position);
                    }
                    break;
                case 1:
                    for (int i = 0; i < numbers.length; i++) {
                        numbers[i] = numbers[i] | 1 << position;
                    }
                    break;
            }

        }
    }
}
