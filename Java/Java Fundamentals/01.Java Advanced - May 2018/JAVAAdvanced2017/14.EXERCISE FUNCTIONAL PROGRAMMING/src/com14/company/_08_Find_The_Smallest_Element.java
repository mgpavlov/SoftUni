package com14.company;
import java.util.*;  // 75/100

public class _08_Find_The_Smallest_Element {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] array = scan.nextLine().split("\\s+");
        List<Integer> list = new ArrayList<>();

        for (String anArray1 : array) {
            Integer anArray = Integer.parseInt(anArray1);
            list.add(anArray);
        }
        int counter=0;
        for (int i = 0; i < array.length; i++) {

            if (Collections.min(list).equals(list.get(i))){
                System.out.printf("%d",counter);
                break;
            }
            counter++;
        }
    }
}
