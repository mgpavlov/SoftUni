package app.ccb.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) {

        try (InputStream resource = this.getClass().getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(resource))) {
            return reader.lines()
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
