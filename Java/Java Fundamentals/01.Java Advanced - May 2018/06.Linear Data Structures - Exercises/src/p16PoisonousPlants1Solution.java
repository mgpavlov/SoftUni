import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p16PoisonousPlants1Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String [] input = reader.readLine().split(" ");
        int[] plants = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            plants[i] = Integer.parseInt(input[i]);
        }

        int[] days = new int[plants.length];
        ArrayDeque<Integer> proximityStack = new ArrayDeque<>();
        proximityStack.push(0);

        for (int i = 1; i < plants.length; i++)
        {
            int maxDays = 0;

            while (proximityStack.size() > 0 && plants[proximityStack.peek()] >= plants[i])
            {
                maxDays = Math.max(maxDays, days[proximityStack.pop()]);
            }
            if (proximityStack.size() > 0)
            {
                days[i] = maxDays + 1;
            }
            proximityStack.push(i);
        }
        System.out.println(getMax(days));
    }

    private static int getMax(int[] inputArray){
        int maxValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }
}
