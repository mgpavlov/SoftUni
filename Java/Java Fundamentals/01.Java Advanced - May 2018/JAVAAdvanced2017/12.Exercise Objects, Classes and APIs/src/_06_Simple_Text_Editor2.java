import java.util.Scanner;
import java.util.Stack;  // 100/100

public class _06_Simple_Text_Editor2 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        String currentString = "";
        Stack<String> lastStrings = new Stack<>();
        lastStrings.push(currentString);

        for (int i = 0; i < n; i++) {
            String[] currentCommand = scan.nextLine().split(" ");
            switch (currentCommand[0]) {
                case "1": {
                    currentString += currentCommand[1];
                    lastStrings.push(currentString);
                }
                break;
                case "2":
                {
                    int countOfLastEl = Integer.parseInt(currentCommand[1]);

                    currentString = currentString.substring(0, currentString.length() - countOfLastEl);
                    lastStrings.push(currentString);
                }
                break;
                case "3": {
                    int indexForPrint = Integer.parseInt(currentCommand[1]);
                    System.out.println(currentString.charAt(indexForPrint - 1));
                }
                break;
                case "4": {
                    lastStrings.pop();
                    currentString = lastStrings.peek();
                }
                break;
            }
        }
    }
}
