package fdmc.util;

import java.io.*;

public class HtmlReaderImpl implements HtmlReader {
    @Override
    public String readHtmlFile(String filePath) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(new File(filePath)),"UTF-8"));
        StringBuilder htmlFileContent = new StringBuilder();
        String htmlLine;
        while ((htmlLine = reader.readLine()) != null) {
                htmlFileContent.append(htmlLine).append(System.lineSeparator());

        }
        return htmlFileContent.toString().trim();
    }
}
