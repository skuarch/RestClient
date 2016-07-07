package restClient.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.EmptyStackException;
import java.util.Locale;
import java.util.Map;

/**
 * abstract class to create a restful client connection.
 *
 * @author skuarch
 */
public abstract class RestfulClient implements AutoCloseable {

    private HttpURLConnection hurlc = null;
    protected static final String UTF_8 = "utf-8";
    protected static final String HURLC_IS_NULL = "hurlc is null";    
    private static final String NO_CONNECTED = "no connected";
    protected static final int HTTP_OK = 200;
    protected static final int HTTP_CREATED = 201;
    protected static final int HTTP_ACCEPTED = 202;
    protected static final int HTTP_PARTIAL_INFO = 203;
    private int responseCode = 0;
    private boolean isConnected = false;
    private String url;

    //==========================================================================
    /**
     * establish connection.
     *
     * @param url String
     * @param requestMethod String
     * @throws IOException in case of error
     */
    public void openConnection(String url, String requestMethod) throws IOException {

        try {

            if (url == null || url.length() < 1) {
                throw new IllegalArgumentException("path is null or empty");
            }

            if (requestMethod == null || requestMethod.length() < 1) {
                throw new IllegalArgumentException("requestMethod is null or empty");
            }

            if (isConnected) {
                throw new IOException("already connected");
            }

            URL u = new URL(url);
            hurlc = (HttpURLConnection) u.openConnection();
            hurlc.setRequestMethod(requestMethod.toUpperCase(Locale.US));

            if (hurlc.getErrorStream() != null) {
                throw new IOException("error establishing connection, please check the url and request method");
            }

            isConnected = true;
            this.url = url;

        } catch (IOException ioe) {
            throw ioe;
        } catch (IllegalArgumentException iae) {
            throw iae;
        }

    }

    //==========================================================================
    /**
     * retrieve the number of response code like 404, 500 or other HTTP code.
     *
     * @return integer
     * @throws Exception in case of error.
     */
    protected int getResponseCode() throws Exception {

        if (!isConnected) {
            throw new ConnectException(NO_CONNECTED);
        }

        responseCode = hurlc.getResponseCode();
        return responseCode;

    }

    //==========================================================================
    /**
     * avoid cache and set the <code>charset</code> to UTF-8.
     *
     * @throws Exception in case of error.
     */
    public void setDefaulProperties() throws Exception {

        if (hurlc == null) {
            throw new IllegalStateException(HURLC_IS_NULL);
        }

        if (!isConnected) {
            throw new ConnectException(NO_CONNECTED);
        }

        hurlc.setInstanceFollowRedirects(false);
        setRequestProperty("charset", UTF_8);
        hurlc.setUseCaches(false);
        hurlc.setDefaultUseCaches(false);
        setRequestProperty("Pragma", "no-cache");
        setRequestProperty("Cache-Control", "no-cache");
        setRequestProperty("Expires", "-1");

    }

    //==========================================================================
    /**
     * set property to the connection.
     *
     * @param property String
     * @param value String
     * @throws IOException in case of error
     */
    protected void setRequestProperty(String property, String value) throws IOException {

        if (property == null || property.length() < 1) {
            throw new IllegalArgumentException("property is null or empty");
        }

        if (value == null || value.length() < 1) {
            throw new IllegalArgumentException("value is null or empty");
        }

        if (hurlc == null) {
            throw new IllegalStateException(HURLC_IS_NULL);
        }

        if (!isConnected) {
            throw new IllegalStateException(NO_CONNECTED);
        }

        hurlc.setRequestProperty(property, value);

    }

    //==========================================================================
    /**
     * set Content-Type.
     *
     * @param contentType String
     * @throws Exception in case of error
     */
    public void setContentType(String contentType) throws Exception {

        if (contentType == null || contentType.length() < 1) {
            throw new IllegalArgumentException("contentType is null or empty");
        }

        setRequestProperty("Content-Type:", contentType);

    }

    //==========================================================================
    /**
     * send text to remote server.
     *
     * @param text String
     * @throws Exception in case of error
     */
    public abstract void sendText(String text) throws Exception;

    //==========================================================================
    /**
     * receive test from remote host.
     *
     * @return String
     * @throws Exception in case of error
     */
    public abstract String receiveText() throws Exception;

    //==========================================================================
    /**
     * close connection.
     *
     * @throws Exception in case of error
     */
    @Override
    public abstract void close() throws Exception;

    //==========================================================================
    /**
     * set credentials.
     *
     * @param credentials String
     * @throws IOException in case of error
     */
    public void setAuthentication(String credentials) throws IOException {

        if (credentials == null || credentials.length() < 1) {
            throw new IllegalArgumentException("credentials are empty or null");
        }

        if (hurlc == null) {
            throw new IllegalStateException("hurlc is null");
        }

        String encoding = Base64.getEncoder().encodeToString(credentials.getBytes(Charset.forName("utf-8")));
        setRequestProperty("Authorization", String.format("Basic %s", encoding));

    }

    //==========================================================================
    /**
     * disconnect from URL.
     *
     * @throws java.lang.Exception in case of error
     */
    protected final void disconnectURL() throws Exception {

        try {

            if (hurlc == null) {
                return;
            }

            if (!isConnected) {
                return;
            }

            String host = hurlc.getURL().getHost();

            if (host == null || host.length() < 1) {
                throw new IOException("host is null or empty");
            }

            hurlc.disconnect();

        } catch (Exception e) {
            throw e;
        } finally {
            hurlc = null;
        }

    }

    //==========================================================================
    /**
     * close OutputStream.
     *
     * @param outputStream OutputStream
     * @throws IOException in case of error
     */
    protected final void closeOutputStream(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            outputStream.close();
        }
    }

    //==========================================================================
    /**
     * close InputStream.
     *
     * @param inputStream InputStream
     * @throws IOException in case of error
     */
    protected final void closeInputStream(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
    }

    //==========================================================================
    /**
     * close BufferedReader.
     *
     * @param bufferedReader BufferedReader
     * @throws IOException in case of error.
     */
    protected final void closeBufferedReader(BufferedReader bufferedReader) throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }

    //==========================================================================
    /**
     * close InputStreamReaer.
     *
     * @param inputStreamReader InputStreamReader
     * @throws Exception in case of error.
     */
    protected final void closeInputStreamReader(InputStreamReader inputStreamReader) throws Exception {
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
    }

    //==========================================================================
    /**
     * creates query parameters.
     *
     * @param parameters Map
     * @return String
     */
    public String getQueryParameters(Map<String, Object> parameters) {

        if (parameters == null || parameters.size() < 1) {
            throw new IllegalArgumentException("parameters are null or empty");
        }

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key == null || key.equals("")) {
                throw new EmptyStackException();
            }

            sb.append(key).append("=").append(value).append("&");
        }

        return sb.toString();

    }

    //==========================================================================
    /**
     * getter.
     *
     * @return String
     */
    public String getUrl() {
        return url;
    }

    //==========================================================================
    /**
     * return the status if connection, default is false.
     *
     * @return boolean
     */
    public boolean isConnected() {
        return isConnected;
    }

    //==========================================================================
    /**
     * return the number of response this method doesn't call to getResponseCode
     * from httpUrlConnection.
     *
     * @return integer and can be 0, when the connection is not established
     */
    public int getResponseCodeCache() {
        return responseCode;
    }

    //==========================================================================
    /**
     * set the status of the connection.
     *
     * @param isConnected boolean
     */
    public void isConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    //==========================================================================
    /**
     * return the HttpURLConnection instance.
     *
     * @return HttpURLConnection
     */
    protected HttpURLConnection getHurlc() {
        return hurlc;
    }

}
