package com.company;

import java.util.*;    // 100/100

public class _07_Find_the_Missing_Number {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Integer n = Integer.parseInt(scan.nextLine());
        String[] area = scan.nextLine().split(", ");
        Set<Integer> set = new TreeSet<>();

        for (String anArea : area) {
            set.add(Integer.parseInt(anArea));
        }

        boolean serched = true;
        Integer missingInteger = 0;

        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                missingInteger = i;
                serched = false;
            }
        }
        System.out.printf("%d", missingInteger);

    }
}
