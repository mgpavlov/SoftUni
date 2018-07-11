import java.util.*;  // 100/100

public class _01_Sorting {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] area = scan.nextLine().split("\\s+");

        int[] arrayIntegers = Arrays.stream(area).mapToInt(Integer::parseInt).toArray();

        bubbleSort(arrayIntegers);

    }

    private static void bubbleSort(int[] numArray) {
        int n = numArray.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (numArray[j - 1] > numArray[j]) {
                    temp = numArray[j - 1];
                    numArray[j - 1] = numArray[j];
                    numArray[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(numArray)
                .replace(",","")
                .replace("]","")
                .replace("[",""));
    }
}
