import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _09_Terrorists_Win3 {   // 100/100
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text = reader.readLine();

        while (true) {
            Integer openPipe = text.indexOf('|');
            if (openPipe == -1) {
                break;
            }
            Integer closePipe = text.indexOf('|', openPipe + 1);
            if (closePipe == -1) {
                break;
            }
            Integer power = 0;
            char[] arrayBeettwenPipes = text.substring(openPipe + 1, closePipe).toCharArray();

            for (Character ch :arrayBeettwenPipes) {
                power += (int) ch;
            }
            power %= 10;
            Integer startInd = Math.max(openPipe - power, 0);
            Integer endInd = Math.min(closePipe + power + 1, text.length());

            text = text.substring(0, startInd)
                    + new String(new char[endInd - startInd]).replace("\0", ".")
                    + text.substring(endInd);

        }
        System.out.println(text);
    }
}
