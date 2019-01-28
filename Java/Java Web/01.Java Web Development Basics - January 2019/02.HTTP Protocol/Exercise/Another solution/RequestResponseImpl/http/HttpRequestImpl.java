package RequestResponseImpl.http;

import RequestResponseImpl.Const;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import java.util.*;

public class HttpRequestImpl implements HttpRequest {

    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private String method;
    private String requestUrl;

    public HttpRequestImpl(String requestContent) {
        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();
        this.parseRequestContent(requestContent);
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.putIfAbsent(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.getRequestUrl().contains(".");
    }

    private void parseRequestContent(String requestContent) {
        String[] lines = requestContent.split("\n");

        this.parseRequestFirstLine(lines[0]);
        String[] remainLines = Arrays.stream(lines).skip(1).toArray(String[]::new);
        String bodyParameters = this.parseRequestHeaders(remainLines);
        if (!Const.EMPTY_STRING.equals(bodyParameters)) {
            this.parseBodyParameters(bodyParameters);
        }
    }

    private void parseRequestFirstLine(String requestLine) {
        String[] requestLineTokens = requestLine.split("\\s+");
        String method = requestLineTokens[0];
        String url = requestLineTokens[1];

        this.setMethod(method);
        this.setRequestUrl(url);
    }

    private String parseRequestHeaders(String[] remainLines) {
        int endHeadersIndex = 0;
        for (int i = 0; i < remainLines.length; i++) {
            if (Const.EMPTY_STRING.equals(remainLines[i].trim())) {
                endHeadersIndex = i;
                break;
            }
            String[] headerTokens = remainLines[i].split(":\\s");
            this.addHeader(headerTokens[0], headerTokens[1]);
        }

        if (endHeadersIndex + 1 < remainLines.length) {
            this.parseBodyParameters(remainLines[endHeadersIndex + 1]);
        }
        return Const.EMPTY_STRING;
    }

    private void parseBodyParameters(String bodyParameters) {
        if (this.getMethod().equalsIgnoreCase(Const.POST)) {

            if (bodyParameters.trim().length() == 0) {
                return;
            }

            Arrays.stream(bodyParameters.split("&"))
                    .map(bp -> bp.split("="))
                    .forEach(bpKvp ->
                            this.addBodyParameter(bpKvp[0], bpKvp[1])
                    );
        }
    }
}
