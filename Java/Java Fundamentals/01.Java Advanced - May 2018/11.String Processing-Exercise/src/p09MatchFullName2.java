import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.regex.Pattern;

public class p09MatchFullName2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String regex = "^[A-Z][a-z]+ [A-Z][a-z]+$";
        while (true){
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
