package main.java.app.httpParserEntities;

import java.util.*;

public class HttpRequestImpl implements HttpRequest {

    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private String method;
    private String requestUrl;

    public HttpRequestImpl(String request) {
        headers = new LinkedHashMap<>();
        bodyParameters = new LinkedHashMap<>();
        parseRequest(request);
    }

    private void parseRequest(String request) {
        List<String> lines = Arrays.asList(request.split("\r\n"));
        if (lines.isEmpty()) {
            return;
        }

        String[] requestLineTokens = lines.get(1).split("\\s+");
        setMethod(requestLineTokens[0]);
        setRequestUrl(requestLineTokens[1]);

        int lineIndex = 2;

        while (lineIndex < lines.size() && !lines.get(lineIndex).isEmpty()){
            String[] kv = lines.get(lineIndex).split(": ");
            addHeader(kv[0], kv[1]);
            lineIndex++;
        }

        if (lineIndex == lines.size() - 2) {
            Arrays.stream(lines.get(lines.size() - 1).split("&"))
                    .map(b->b.split("="))
                    .forEach(kv -> addBodyParameter(kv[0], kv[1]));
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return bodyParameters;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return requestUrl.contains(".");
    }
}
