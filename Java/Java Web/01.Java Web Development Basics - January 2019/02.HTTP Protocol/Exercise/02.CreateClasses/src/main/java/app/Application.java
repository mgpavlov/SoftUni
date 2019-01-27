package main.java.app;


import main.java.app.httpParserEntities.HttpRequest;
import main.java.app.httpParserEntities.HttpRequestImpl;
import main.java.app.httpParserEntities.HttpResponse;
import main.java.app.httpParserEntities.HttpResponseImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        Set<String> validUrls = parseUrls(request.toString().split(System.lineSeparator())[0]);
        HttpRequest httpRequest = new HttpRequestImpl(request.toString());
        HttpResponse httpResponse = buildResponse(validUrls, httpRequest);
        System.out.println(new String(httpResponse.getBytes(), StandardCharsets.UTF_8));

    }

    private static HttpResponse buildResponse(Set<String> validUrls, HttpRequest request) {
        HttpResponse response = new HttpResponseImpl();

        request.getHeaders()
                .entrySet()
                .stream()
                .filter(header -> Set.of("Date", "Host", "Content-Type").contains(header.getKey()))
                .forEach(header -> response.addHeader(header.getKey(), header.getValue()));

        if (!validUrls.contains(request.getRequestUrl())) {
            response.setStatusCode(404);
            response.setContent("The requested functionality was not found.".getBytes(StandardCharsets.UTF_8));
        } else if (!request.getHeaders().containsKey("Authorization")) {
            response.setStatusCode(401);
            response.setContent("You are not authorized to access the requested functionality.".getBytes(StandardCharsets.UTF_8));
        } else if ((request.getMethod().equals("POST")) && request.getBodyParameters().isEmpty()) {
            response.setStatusCode(400);
            response.setContent("There was an error with the requested functionality due to malformed request.".getBytes(StandardCharsets.UTF_8));
        } else {
            response.setStatusCode(200);
            String username = decodeAuthorization(request.getHeaders().get("Authorization"));
            switch (request.getMethod()) {
                case "POST":
                    String itemName = request.getBodyParameters().entrySet().stream().findFirst().orElseThrow().getValue();
                    String itemParts = request.getBodyParameters().entrySet()
                            .stream()
                            .skip(1)
                            .map(param -> String.format("%s - %s", param.getKey(), param.getValue()))
                            .collect(Collectors.joining(", "));
                    response.setContent(String.format("Greetings %s! You have successfully created %s with %s.", username, itemName, itemParts)
                            .getBytes(StandardCharsets.UTF_8));
                    break;
                case "GET":
                    response.setContent(String.format("Greetings %s!", username).getBytes(StandardCharsets.UTF_8));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown or unsupported HTTP method: " + request.getMethod());
            }
        }

        return response;
    }

    private static String decodeAuthorization(String encoded) {
        if (!encoded.startsWith("Basic ")) {
            throw new IllegalArgumentException("Unknown encoding for string: " + encoded);
        }
        return new String(Base64.getDecoder()
                .decode(encoded.substring("Basic ".length()).getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);
    }

    private static Set<String> parseUrls(String urlsLine) {
        Set<String> urls = new HashSet<>();
        if (urlsLine != null) {
            Matcher matcher = Pattern.compile("/[^ ]+").matcher(urlsLine);
            while (matcher.find()) {
                urls.add(matcher.group());
            }
        }
        return urls;
    }

}
