//import java.util.Scanner;
//
//public class _07_Collect_the_Coins {
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//
//        char[][] matrix = new char[4][];
//
//        for (int i = 0; i < 4; i++) {
//            matrix[i] = scan.nextLine().toCharArray();
//        }
//        char[] moves = scan.nextLine().toCharArray();
//
//        int counterCouns = 0;
//        int counterWalls = 0;
//        int startRow = 0;
//        int startCol = 0;
//
////        if (matrix[startRow][startCol] == '$') {
////            counterCouns++;
////        }
//
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                for (int last = 0; last < moves.length - 1; last++) {
//
//                    try {
//                        if (moves[last] == 'V') {
//                            matrix[startRow][startCol] = matrix[startRow + 1][startCol];
//                        }
//                    } catch (IndexOutOfBoundsException e)‏{
//                        counterWalls++;
//                    }
//
//                }
//
//            }
//        }
//
//        System.out.printf("Coins collected: %d\n", counterCouns);
//        System.out.printf("Walls hit: %d\n", counterWalls);
//    }
//}
