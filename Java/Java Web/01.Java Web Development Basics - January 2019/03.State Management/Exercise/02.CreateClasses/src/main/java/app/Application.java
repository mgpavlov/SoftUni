package main.java.app;

import main.java.app.http.HttpRequest;
import main.java.app.http.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder request = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            request.append(line).append(System.lineSeparator());
            if (line.isEmpty()) {
                break;
            }
        }
        request.append(reader.readLine());

        HttpRequest httpRequest = new HttpRequestImpl(request.toString());

        StringBuilder cookiesData = new StringBuilder();

        httpRequest.getCookies().forEach(cookie->{
            cookiesData.append(cookie.getKey()).append(" <-> ").append(cookie.getValue()).append(System.lineSeparator());
        });

        System.out.println(cookiesData.toString());
    }
}
