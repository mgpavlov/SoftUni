package main.java.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    /*private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static String currentDate = LocalDate.now().format(DATE_FORMATTER);*/


    public static void main(String[] args) throws IOException {
        List<String> validUrls = getValidUrls();
        List<String> request = getRequest();

        String method = request.get(0).split("\\s+")[0];
        String url = request.get(0).split("\\s+")[1];

        Map<String, String> headers = getHeaders(request.subList(1, request.size()));
        Map<String, String> bodyParams = getBody(request);

        StringBuilder response = new StringBuilder();
        if (!validUrls.contains(url)){
            response.append("HTTP/1.1 404 Not Found").append(System.lineSeparator());

            appendHeadersToResponse(headers, response);

            response.append(System.lineSeparator()).append("The requested functionality was not found.");
        } else if (!headers.containsKey("Authorization")){
            response.append("HTTP/1.1 401 Unauthorized").append(System.lineSeparator());

            appendHeadersToResponse(headers, response);

            response.append(System.lineSeparator()).append("You are not authorized to access the requested functionality.");
        } else if (method.equals("POST") && bodyParams.isEmpty()){
            response.append("HTTP/1.1 400 Bad Request").append(System.lineSeparator());

            appendHeadersToResponse(headers, response);

            response.append(System.lineSeparator()).append("There was an error with the requested functionality due to malformed request.");
        }else if (method.toUpperCase().equals("GET")){
            appendBodyForResponseOK200(headers, response);
        }else {
            appendBodyForResponseOK200(headers, response);
            appendAdditionalResponseBodyForPostMethod(bodyParams, response);

        }
        System.out.println(response.toString());
    }

    private static void appendBodyForResponseOK200(Map<String, String> headers, StringBuilder response) {
        response.append("HTTP/1.1 200 OK").append(System.lineSeparator());

        appendHeadersToResponse(headers, response);

        String username = new String(Base64.getDecoder().decode(headers.get("Authorization").split("\\s+")[1]));
        response.append(System.lineSeparator()).append(String.format("Greetings %s!", username));
    }

    private static void appendAdditionalResponseBodyForPostMethod(Map<String, String> bodyParams, StringBuilder response) {
        String firstBodyParamK ="";
        String secondBodyParamK ="";
        String thirdBodyParamK="";

        int n = 1;
        for (String paramKey : bodyParams.keySet()) {
            switch (n){
                case 1:
                    firstBodyParamK = paramKey;
                    break;
                case 2:
                    secondBodyParamK = paramKey;
                    break;
                case 3:
                    thirdBodyParamK = paramKey;
                    break;
            }
            n++;
        }

        response.append(String.format(
                " You have successfully created %s with %s - %s, %s - %s."
                , bodyParams.get(firstBodyParamK),
                secondBodyParamK, bodyParams.get(secondBodyParamK),
                thirdBodyParamK, bodyParams.get(thirdBodyParamK))
        );
    }

    private static void appendHeadersToResponse(Map<String, String> headers, StringBuilder response) {
        /*if (!headers.keySet().contains("Date")){
            response.append(String.format("Date: %s", currentDate)).append(System.lineSeparator());
        }*/

        headers.entrySet().stream().filter(h->h.getKey().equals("Date")||h.getKey().equals("Host")|| h.getKey().equals("Content-Type")).forEach(h->{
            response.append(h.getKey()).append(": ").append(h.getValue()).append(System.lineSeparator());
        });
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static List<String> getRequest() throws IOException {
        List<String> request = new ArrayList<>();

        String line = null;
        while ((line = reader.readLine()) != null && line.length() > 0){
            request.add(line);
        }

        request.add(System.lineSeparator());
        if ((line = reader.readLine()) != null && line.length() > 0){
            request.add(line);
        }

        return request;
    }

    private static Map<String, String> getHeaders(List<String> request) {
        Map<String, String> headers = new LinkedHashMap<>();
        for (String header : request) {
            if (header.equals(System.lineSeparator())){
                break;
            }
            if (!header.contains(": ")){
                continue;
            }
            String[] headerKV = header.split(": ");
            headers.put(headerKV[0].trim(), headerKV[1].trim());
        }
        return headers;
    }

    private static Map<String, String> getBody(List<String> request) {
        String body = request.get(request.size() - 1);
        Map<String, String> bodyParams = new LinkedHashMap<>();
        if (body.equals(System.lineSeparator()) || body.length() == 0){
            return bodyParams;
        }

        String[] params = body.split("&");
        for (String param : params) {
            String[] paramKV = param.split("=");
            bodyParams.put(paramKV[0], paramKV[1]);
        }
        return bodyParams;
    }
}
