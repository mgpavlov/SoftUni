import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;  // 0/100
import java.util.TreeMap;

public class _06_Simple_Text_Editor {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer n = Integer.parseInt(scan.nextLine());
        StringBuffer text = new StringBuffer();
        TreeMap<Integer, String> dict = new TreeMap<>();
        Integer counter = 0;

        for (int i = 0; i < n; i++) {
            String[] current = scan.nextLine().split("\\s+");

            if (current[0].equals("1")) {
                dict.put(counter, text.toString());
                text.append(current[1]);
                counter++;
            } else if (current[0].equals("2")) {
                text.delete(0, Integer.parseInt(current[1]));

                dict.put(counter, text.toString());
                counter++;
            } else if (current[0].equals("3")) {
                Integer index = Integer.parseInt(current[1]);
                System.out.println(text.charAt(index-1));
            } else if (current[0].equals("4")) {

                long lastKey = 0;
                for (Map.Entry<Integer, String> entry : dict.entrySet()) {
                    lastKey = entry.getKey();
                }
                Object key = dict.keySet().toArray(new Object[dict.size()])[counter-1];
                Object value = dict.get(key);
                System.out.printf("%s",String.valueOf(value));
            }
        }
    }
}
