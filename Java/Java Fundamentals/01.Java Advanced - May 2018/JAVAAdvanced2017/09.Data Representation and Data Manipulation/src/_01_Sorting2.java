import java.util.Arrays;
import java.util.Scanner;

public class _01_Sorting2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] nums = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j - 1] > nums[j]) {
                    swapElements(nums, j);
                }
            }
        }

        System.out.println(Arrays.toString(nums).replaceAll("[\\[\\],]", ""));
    }

    private static void swapElements(int[] nums, int j) {
        int temp = nums[j - 1];
        nums[j - 1] = nums[j];
        nums[j] = temp;
    }
}
