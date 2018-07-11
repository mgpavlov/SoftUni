import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;   // 0/100

public class _11_String_Matrix_Rotation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        Integer degree = Integer.parseInt(command.substring(7, command.length() - 1)) % 360;

        List<String> originalMatrix = new ArrayList<>();

        while (true) {
            String input = scan.nextLine();
            originalMatrix.add(input);
            if (input.equals("exam")) {
                break;
            }
        }

        if (degree == 0) {
            for (String anOriginalMatrix : originalMatrix) {
                System.out.println(String.valueOf(anOriginalMatrix).replace("\0", ""));
            }
        } else if (degree == 90) {

                for (int col = 0; col < originalMatrix.get(0).length(); col++) {
                    String str = "";
                    for (int row = originalMatrix.size() - 1; row >= 0; row--) {
                        str += originalMatrix.get(row).charAt(col);
                    }
                    System.out.println(str);

            }
        } else if (degree == 180) {

        } else if (degree == 270) {

        }
    }
}
