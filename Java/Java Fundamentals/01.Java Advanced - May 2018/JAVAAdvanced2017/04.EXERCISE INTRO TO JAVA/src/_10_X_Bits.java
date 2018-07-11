import java.io.IOException;
import java.util.Scanner;     // 100/100

public class _10_X_Bits {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String[] binaries = new String[8];

        for (int i = 0; i < 8; i++) {
            String binary = String.format("%32s", Integer.toBinaryString(Integer.parseInt(scan.nextLine()))).replace(' ', '0');
            binaries[i] = binary;
        }
        Integer counter = 0;
        for (int i = 0; i < binaries.length - 2; i++) {
            for (int j = 0; j < binaries[i].length() - 2; j++) {
                String a = binaries[i].charAt(j) + "" + binaries[i].charAt(j + 1) + binaries[i].charAt(j + 2);
                String b = binaries[i + 1].charAt(j) + "" + binaries[i + 1].charAt(j + 1) + binaries[i + 1].charAt(j + 2);
                String c = binaries[i + 2].charAt(j) + "" + binaries[i + 2].charAt(j + 1) + binaries[i + 2].charAt(j + 2);

                if (a.equals("101") && b.equals("010") && c.equals("101")){
                    counter++;
                }
            }
        }
        System.out.printf("%d",counter);
    }
}
