package com.company;

import java.util.Scanner; // 100/100

public class _04_Combinations_Count {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer n = Integer.parseInt(scan.nextLine());
        Integer k = Integer.parseInt(scan.nextLine());

        Long x = binomial(n, k);

        System.out.printf("%d%n",x );
    }

    private static long binomial(int n, int k) {
        if (k > n - k)
            k = n - k;

        long b = 1;
        for (int i = 1, m = n; i <= k; i++, m--)
            b = b * m / i;
        return b;
    }
}
