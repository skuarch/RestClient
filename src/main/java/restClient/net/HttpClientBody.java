package restClient.net;

import restClient.model.RestfulClientBody;
import java.io.IOException;

/**
 * wrapper of RestfulClientBody.
 *
 * @author skuarch
 */
public class HttpClientBody implements AutoCloseable {

    private final String url;
    private final String requestMethod;
    private String credentials = null;
    private RestfulClientBody rcb = null;

    //==========================================================================
    /**
     * Constructor.
     *
     * @param url String
     * @param requestMethod String
     * @param credentials String
     * @throws Exception in case of error.
     */
    public HttpClientBody(String url, String requestMethod, String credentials) throws Exception {
        this.url = url;
        this.requestMethod = requestMethod;
        this.credentials = credentials;
        rcb = new RestfulClientBody();
    }

    //==========================================================================
    /**
     * Constructor.
     * @param url String
     * @param requestMethod String
     */
    public HttpClientBody(String url, String requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
        rcb = new RestfulClientBody();
    }

    //==========================================================================
    /**
     * open connection.
     *
     * @throws IOException in case of error.
     */
    public void openConnection() throws IOException {

        if (requestMethod == null || requestMethod.length() < 1) {
            throw new NullPointerException("requestMethod is null or empty");
        }

        if (requestMethod.equalsIgnoreCase("get")) {
            throw new UnsupportedOperationException("GET is not supported in body request");
        }

        rcb.openConnection(url, requestMethod);

        if (credentials != null) {
            rcb.setAuthentication(credentials);
        }
    }

    //==========================================================================
    /**
     * send text to remote server.
     *
     * @param text String
     * @throws Exception in case of error.
     */
    public void sendText(String text) throws Exception {

        try {

            if (credentials != null) {
                rcb.setAuthentication(credentials);
            }

            rcb.setDefaulProperties();
            rcb.sendText(text);

        } catch (Exception e) {
            rcb.close();
            throw e;
        }

    }

    //==========================================================================
    /**
     * receive text from remote host.
     *
     * @return String
     * @throws Exception in case of error.
     */
    public String receiveText() throws Exception {

        String result = null;

        try {
            result = rcb.receiveText();
        } catch (Exception e) {
            rcb.close();
            throw e;
        }

        return result;

    }

    //==========================================================================
    /**
     * close connection.
     *
     * @throws Exception in case of error.
     */
    @Override
    public void close() throws Exception {

        try {
            rcb.close();
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    /**
     * return a number with the response code previously cached.
     *
     * @return <code>int</code>
     */
    public int getResponceCode() {
        return rcb.getResponseCodeCache();
    }

}
