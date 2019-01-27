package main.java.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        List<String> request = getRequest();

        Map<String, String> headers = getHeaders(request.subList(1, request.size()));
        Map<String, String> bodyParams = getBody(request);
        Map<String, String> cookies = getCookies(headers);


        StringBuilder cookiesData = new StringBuilder();

        cookies.forEach((key, value) -> cookiesData.append(key).append(" -> ").append(value).append(System.lineSeparator()));

        System.out.println(cookiesData.toString());
    }

    private static Map<String, String> getCookies(Map<String, String> headers) {
        Map<String, String> cookies = new LinkedHashMap<>();

        if (headers.containsKey("Cookie")){
            Arrays.stream(headers.get("Cookie").split("; ")).forEach(c->{
                String[] cookieKV = c.split("=");
                cookies.put(cookieKV[0], cookieKV[1]);
            });
        }
        return cookies;
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
