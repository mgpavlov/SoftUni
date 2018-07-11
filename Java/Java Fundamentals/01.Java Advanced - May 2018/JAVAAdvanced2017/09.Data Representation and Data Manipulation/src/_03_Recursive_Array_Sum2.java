import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _03_Recursive_Array_Sum2
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(bf.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int sum = sumElementsOfArray(nums, 0);
        System.out.println(sum);
    }

    private static int sumElementsOfArray(int[] nums, int index) {
        if (index == nums.length - 1) {
            return nums[index];
        }

        return nums[index] + sumElementsOfArray(nums, index + 1);
    }
}
