package RequestResponseImpl;

import RequestResponseImpl.http.HttpRequest;
import RequestResponseImpl.http.HttpRequestImpl;
import RequestResponseImpl.http.HttpResponse;
import RequestResponseImpl.http.HttpResponseImpl;
import RequestResponseImpl.io.Reader;
import RequestResponseImpl.io.Writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class App implements Runnable {

    private Reader reader;
    private Writer writer;

    public App(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() {
        List<String> validUrls = new ArrayList<>();
        String inputRequest = null;
        try {
            validUrls = getValidUrls();
            inputRequest = getRequest();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        HttpResponse httpResponse = new HttpResponseImpl();
        if (inputRequest != null) {
            HttpRequest httpRequest = new HttpRequestImpl(inputRequest);

            extractHeaders(httpResponse, httpRequest);
            setResponseStatusCode(validUrls, httpResponse, httpRequest);
        }

        this.writer.writeLine(new String(httpResponse.getBytes()));
    }

    private void setResponseStatusCode(List<String> validUrls, HttpResponse httpResponse, HttpRequest httpRequest) {
        if (!validUrls.contains(httpRequest.getRequestUrl())) {
            httpResponse.setStatusCode(Const.STATUS_CODE_404);
            httpResponse.setContent(Const.DEFAULT_404_BODY.getBytes());
        } else if (!httpRequest.getHeaders().containsKey(Const.AUTH)) {
            httpResponse.setStatusCode(Const.STATUS_CODE_401);
            httpResponse.setContent(Const.DEFAULT_401_BODY.getBytes());
        } else if (Const.POST.equalsIgnoreCase(httpRequest.getMethod())
                && httpRequest.getBodyParameters().size() == 0) {
            httpResponse.setStatusCode(Const.STATUS_CODE_400);
            httpResponse.setContent(Const.DEFAULT_400_BODY_POST.getBytes());
        } else {
            httpResponse.setStatusCode(Const.STATUS_CODE_200);
            String bodyContent = buildStatus200BodyContent(httpRequest);
            httpResponse.setContent(bodyContent.getBytes());
        }
    }

    private String buildStatus200BodyContent(HttpRequest httpRequest) {
        String username = getDecodedUsername(httpRequest);

        List<String> paramsKeySet = new ArrayList<>(httpRequest.getBodyParameters().keySet());
        return String.format(Const.DEFAULT_200_OK_BODY,
                username,
                httpRequest.getBodyParameters().get(paramsKeySet.get(0)),
                paramsKeySet.get(1),
                httpRequest.getBodyParameters().get(paramsKeySet.get(1)),
                paramsKeySet.get(2),
                httpRequest.getBodyParameters().get(paramsKeySet.get(2))
        );
    }

    private String getDecodedUsername(HttpRequest httpRequest) {
        String base64Username = httpRequest.getHeaders().get(Const.AUTH).split("\\s+")[1];
        byte[] decodedUsername = Base64.getDecoder().decode(base64Username);
        return new String(decodedUsername);
    }

    private void extractHeaders(HttpResponse httpResponse, HttpRequest httpRequest) {
        httpRequest.getHeaders()
                .forEach((headerName, headerValue) -> {
                    if (Const.PROCESS_HEADER_DATE.equalsIgnoreCase(headerName)
                            || Const.PROCESS_HEADER_HOST.equalsIgnoreCase(headerName)
                            || Const.PROCESS_HEADER_CONTENT_TYPE.equalsIgnoreCase(headerName)) {
                        httpResponse.addHeader(headerName, headerValue);
                    }
                });
    }

    private List<String> getValidUrls() throws IOException {
        return Arrays.asList(this.reader.readLine().split("\\s+"));
    }

    private String getRequest() throws IOException {
        StringBuilder requestBuilder = new StringBuilder();

        String line;
        while ((line = this.reader.readLine()) != null) {
            if (line.length() == 0) {
                requestBuilder.append(System.lineSeparator());

                if (this.reader.ready()) {
                    if ((line = reader.readLine()) != null && line.length() > 0) {
                        requestBuilder.append(line);
                    }
                }
                break;
            }
            requestBuilder.append(line).append(System.lineSeparator());
        }
        return requestBuilder.toString();
    }
}
