import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;     // SearchingBinary
import java.util.Arrays;

public class _02_Searching2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int searchedElement = Integer.parseInt(br.readLine());
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int middle = (int) Math.floor(left + right) / 2;
            int middleElement = nums[middle];

            if (searchedElement < middleElement) {
                right = middle - 1;
            } else if (searchedElement > middleElement) {
                left = middle + 1;
            } else {
                index = middle;
                break;
            }
        }

        System.out.println(index);
    }
}
