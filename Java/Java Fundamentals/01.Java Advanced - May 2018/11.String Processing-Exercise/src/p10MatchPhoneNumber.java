import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.regex.Pattern;

public class p10MatchPhoneNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String regex = "^\\+359( |-)2\\1[0-9]{3}\\1[0-9]{4}$";

        while(true){
            String input = reader.readLine();
            if ("end".equals(input)){
                break;
            }
            if (Pattern.matches(regex, input)){
                System.out.println(input);
            }
        }
    }
}
