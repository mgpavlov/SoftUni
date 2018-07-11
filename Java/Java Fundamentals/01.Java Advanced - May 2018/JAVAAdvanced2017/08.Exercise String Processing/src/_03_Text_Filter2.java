package src;

import java.util.Scanner;  //100/100

public class _03_Text_Filter2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] banList = scanner.nextLine().split(", ");
        String text = scanner.nextLine();

        for (String banned : banList) {
            if (text.contains(banned)) {
                String replacement = new String(new char[banned.length()]).replace("\0", "*");
                text = text.replaceAll(banned, replacement);
            }
        }

        System.out.println(text);
    }
}
