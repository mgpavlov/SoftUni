import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p10MatchPhoneNumber2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String regex = "^\\+359( |-)2\\1[0-9]{3}\\1[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);

        while(true){
            String input = reader.readLine();
            Matcher matcher = pattern.matcher(input);
            if ("end".equals(input)){
                break;
            }
            if (matcher.find()){
                System.out.println(input);
            }
        }
    }
}
