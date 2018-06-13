import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class p16ExtractHyperlinks {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder html = new StringBuilder();

        while (true){
            String input = reader.readLine();
            if ("END".equals(input)){
                break;
            }
            html.append(input).append("\n");
        }

        String regex = "<a\\s+[^>]*href\\s*=\\s*('([^']*)'|\"([^\"]*)\"|([^\\s>]+))[^>]*>";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html.toString());

        while (matcher.find()){
            if (matcher.group(2)!=null)System.out.println(matcher.group(2));
            if (matcher.group(3)!=null)System.out.println(matcher.group(3));
            if (matcher.group(4)!=null)System.out.println(matcher.group(4));
        }

    }
}
