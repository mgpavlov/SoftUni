package main.java.app.httpParserEntities;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {

    private Map<String, String> headers;
    private byte[] content;
    private int statusCode;

    private Map<Integer, String> statusCodeMessage;

    public HttpResponseImpl() {
        content = new byte[0];
        headers = new HashMap<>();
        statusCodeMessage = new HashMap<>();
    }

    private Map<Integer, String> getStatusCodeMessage() {
        this.statusCodeMessage.put(200, "OK");
        this.statusCodeMessage.put(400, "Bad Request");
        this.statusCodeMessage.put(401, "Unauthorized");
        this.statusCodeMessage.put(404, "Not Found");

        return this.statusCodeMessage;
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public byte[] getContent() {
        return content.clone();
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content.clone();
    }

    @Override
    public byte[] getBytes() {

        StringBuilder response = new StringBuilder();
                response
                        .append("HTTP/1.1")
                        .append(" ")
                        .append(getStatusCode())
                        .append(" ").append(getStatusCodeMessage().get(getStatusCode()))
                        .append(System.lineSeparator());

        headers.forEach((key, value) -> response
                .append(key)
                .append(": ")
                .append(value)
                .append(System.lineSeparator()));

        response.append(System.lineSeparator())
                .append(new String(content, StandardCharsets.UTF_8));

        return response.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void addHeader(String header, String value) {
        headers.put(header, value);
    }
}
