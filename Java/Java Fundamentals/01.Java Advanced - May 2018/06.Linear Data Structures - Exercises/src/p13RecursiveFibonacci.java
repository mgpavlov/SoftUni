import java.util.Scanner;

public class p13RecursiveFibonacci {
    private static long[] memo;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = Integer.parseInt(input.nextLine());

        memo = new long[n + 1];
        System.out.println(recursiveFibonacciWithMemoization(n));
    }

    // Recursive fibonacci without DP
    private static long recursiveFibonacci(int n) {
        if (n <= 1) {
            return 1;
        }

        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    // Top down DP: recursion + memoization
    private static long recursiveFibonacciWithMemoization(int n) {
        if (n <= 1) {
            return 1;
        }

        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] =
                recursiveFibonacciWithMemoization(n - 1) +
                        recursiveFibonacciWithMemoization(n - 2);
        return memo[n];
    }

    // Bottom up DP
    private static long fibonacciWithBottomUpDP(int n) {
        long[] fibonacciNumbers = new long[n + 1];

        fibonacciNumbers[0] = 1;
        fibonacciNumbers[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibonacciNumbers[i] =
                    fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2];
        }

        return fibonacciNumbers[n];
    }
}
