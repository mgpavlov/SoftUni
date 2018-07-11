import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] commandArgs = in.nextLine().split("[()]");
        ArrayList<String> matrix = new ArrayList<>();


        Integer longestRow = 0;

//        while (!command.equals("END")) {
//            if (command.length() > longestRow) {
//                longestRow = command.length();
//            }
//            matrix.add(command);
//            command = in.nextLine();
//        }
        while (true) {
            String command = in.nextLine();
            if (command.equals("ЕND")) {
                break;
            }
            if (command.length() > longestRow) {
                longestRow = command.length();
            }
            matrix.add(command);
        }

        int angle = Integer.parseInt(commandArgs[1]);

        switch (angle % 360) {
            case 90:
                for (int i = 0; i < longestRow; i++) {
                    for (int j = matrix.size() - 1; j >= 0; j--) {
                        if (i >= matrix.get(j).length())
                            System.out.printf(" ");
                        else
                            System.out.print(matrix.get(j).charAt(i));
                    }

                    System.out.println();
                }
                break;
            case 180:
                for (int i = matrix.size() - 1; i >= 0; i--) {
                    String toPrint = new StringBuilder(matrix.get(i)).reverse().toString();
                    System.out.println(padLeft(toPrint, longestRow));
                }
                break;
            case 270:
                for (int i = longestRow - 1; i >= 0; i--) {
                    for (String aMatrix : matrix) {
                        if (i >= aMatrix.length())
                            System.out.print(" ");
                        else
                            System.out.print(aMatrix.charAt(i));
                    }

                    System.out.println();
                }
                break;
            case 0:
                for (String aMatrix : matrix) {
                    System.out.println(aMatrix);
                }
                break;
        }
    }

    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }
}