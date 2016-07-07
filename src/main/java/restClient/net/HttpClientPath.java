package restClient.net;

import java.io.IOException;
import restClient.model.RestfulClientPath;

/**
 * wrapper of RestfulClientPath.
 *
 * @author skuarch
 */
public class HttpClientPath implements AutoCloseable {

    private final String url;
    private final String requestMethod;
    private String credentials = null;
    private RestfulClientPath rcp = null;

    //==========================================================================
    /**
     * constructor.
     *
     * @param url String
     * @param requestMethod String
     * @param credentials String
     * @throws Exception in case of error
     */
    public HttpClientPath(String url, String requestMethod, String credentials) throws Exception {

        this.url = url;
        this.requestMethod = requestMethod;
        this.credentials = credentials;
        rcp = new RestfulClientPath();

    }

    //==========================================================================
    /**
     * Constructor.
     *
     * @param url String
     * @param requestMethod String
     */
    public HttpClientPath(String url, String requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
        rcp = new RestfulClientPath();
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

        rcp.openConnection(url, requestMethod);

        if (credentials != null) {
            rcp.setAuthentication(credentials);
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
                rcp.setAuthentication(credentials);
            }

            rcp.setDefaulProperties();
            rcp.sendText(text);

        } catch (Exception e) {
            rcp.close();
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
            result = rcp.receiveText();
        } catch (Exception e) {
            rcp.close();
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
            rcp.close();
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
        return rcp.getResponseCodeCache();
    }

}
