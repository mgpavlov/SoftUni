package com.company;

import java.util.*; // 100/100

public class _05_Chocolates {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer barSize = Integer.parseInt(scan.nextLine());

        List<Integer> list = new ArrayList<>();
        String[] input = scan.nextLine().split(", ");

        for (String anInput : input) {
            list.add(Integer.parseInt(anInput));
        }

        Integer minStudents = Integer.parseInt(scan.nextLine());
        Collections.sort(list);

        Integer min = 0;
        Integer max = 0;

        Integer MinDiffernce = Integer.MAX_VALUE;

        for (int i = 0; i < barSize; i++) {

            if (i + minStudents - 1 < list.size()) {
                min = list.get(i);
                max = list.get(i + minStudents - 1);

                if (max - min < MinDiffernce) {
                    MinDiffernce = max - min;
                }
            }
        }
        System.out.printf("Min Difference is %s.", MinDiffernce);
    }
}