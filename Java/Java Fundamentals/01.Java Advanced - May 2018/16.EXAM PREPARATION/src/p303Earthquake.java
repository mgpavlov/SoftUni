import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class p303Earthquake {
    public static void main(String[] args) throws IOException {
        String s ="aabajaab";

       s.chars().filter(c->c!='a').forEach(c->{
            System.out.println((char) c);
        });

    }
}
