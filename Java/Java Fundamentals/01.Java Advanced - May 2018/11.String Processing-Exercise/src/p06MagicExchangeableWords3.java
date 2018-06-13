import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class p06MagicExchangeableWords3 { //80/100
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split("\\s+");

        StringBuilder longerWord = new StringBuilder(str[0]);
        StringBuilder shorterWord = new StringBuilder(str[1]);
        StringBuilder temp = new StringBuilder();


        if (shorterWord.length() > longerWord.length()){
            temp = longerWord;
            longerWord = shorterWord;
            shorterWord = temp;
        }


        Map <Character, Character> result = new LinkedHashMap<>();
        for (int i = 0; i < shorterWord.length(); i++) {
            if (!result.containsKey(longerWord.charAt(i))){
                if (result.containsValue(shorterWord.charAt(i))){
                    System.out.println(false);
                    return;
                }
                result.put(longerWord.charAt(i), shorterWord.charAt(i));
            }else{
                if (!result.get(longerWord.charAt(i)).equals(shorterWord.charAt(i))){
                    System.out.println(false);
                    return;
                }
            }
        }


        /*for (int i = shorterWord.length(); i < longerWord.length(); i++) {
            if (longerWord.charAt(shorterWord.length()-1) != longerWord.charAt(i)){
                System.out.println(false);
                return;
            }

        }*/
        System.out.println(true);
    }
}
