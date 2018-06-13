import java.util.HashMap;
        import java.util.Map;
        import java.util.Scanner;

public class p04CountSameValuesInArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] array = sc.nextLine().split(" ");

        Map<String, Integer> data = new HashMap<>();

        for (String el : array) {
            if (!data.containsKey(el)){
                data.put(el, 0);
            }
            data.put(el, data.get(el)+1);
        }
        for (Map.Entry<String, Integer> num : data.entrySet()) {
            System.out.printf("%s - %d times%n", num.getKey(), num.getValue());
        }
    }
}

