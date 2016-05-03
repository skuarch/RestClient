package model.net.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.EmptyStackException;
import java.util.Map;

/**
 * abstract class to create a restful client connection.
 *
 * @author skuarch
 */
public abstract class RestfulClient {

    protected OutputStream outputStream = null;
    protected InputStream inputStream = null;
    protected BufferedReader bufferedReader = null;
    protected HttpURLConnection hurlc = null;
    protected static final String UTF_8 = "utf-8";
    protected static final String HURLC_IS_NULL = "hurlc is null";
    protected static final String WRONG_RESPONSE_CODE = "response code is not 200";
    protected static final int HTTP_OK = 200;
    private int responseCode = 0;
    private boolean isConnected = false;

    //==========================================================================
    /**
     * establish the connection.
     *
     * @param path
     * @param requestMethod
     * @throws IOException in case of error
     */
    protected void openConnection(String path, String requestMethod) throws IOException {

        try {

            if (path == null || path.length() < 1) {
                throw new IllegalArgumentException("path is null or empty");
            }

            if (path == null) {
                throw new IllegalArgumentException("path is null or empty");
            }

            if (requestMethod == null || requestMethod.length() < 1) {
                throw new IllegalArgumentException("requestMethod is null or empty");
            }

            URL url = new URL(path);
            hurlc = (HttpURLConnection) url.openConnection();

            if (hurlc.getErrorStream() != null) {
                throw new IOException("error establishing connection, please check the url and request method");
            }

            isConnected = true;

        } catch (IllegalArgumentException | IOException e) {
            throw e;
        }

    }

    //==========================================================================
    protected int getResponseCode() throws Exception {

        try {
            responseCode = hurlc.getResponseCode();
        } catch (Exception e) {
            throw e;
        }

        return responseCode;
    }

    //==========================================================================
    protected void setDefaulProperties() throws Exception {

        if (hurlc == null) {
            throw new IllegalStateException(HURLC_IS_NULL);
        }

        if (!isConnected) {
            throw new ConnectException("no connected");
        }

        try {

            hurlc.setInstanceFollowRedirects(false);
            //hurlc.setRequestMethod(requestMethod);
            setRequestProperty("charset", UTF_8);
            hurlc.setUseCaches(false);
            hurlc.setDefaultUseCaches(false);
            setRequestProperty("Pragma", "no-cache");
            setRequestProperty("Cache-Control", "no-cache");
            setRequestProperty("Expires", "-1");

        } catch (Exception e) {
            throw e;
        }

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
            throw new IllegalStateException("no connected");
        }

        hurlc.setRequestProperty(property, value);

    }

    /**
     * send text to remote server.
     *
     * @param text String
     * @throws IOException in case of error
     */
    protected abstract void sendText(String text) throws IOException;

    /**
     * receive test from remote host.
     *
     * @return String
     * @throws IOException in case of error
     */
    protected abstract String receiveText() throws IOException;

    //==========================================================================
    /**
     * set credentials.
     *
     * @param credentials String
     * @throws IOException in case of error
     */
    protected void setAuthentication(String credentials) throws IOException {

        if (credentials == null || credentials.length() < 1) {
            throw new IllegalArgumentException("creadentials are empty or null");
        }

        if (hurlc == null) {
            throw new IllegalStateException("hurlc is null");
        }

        String encoding = Base64.getEncoder().encodeToString(credentials.getBytes(Charset.forName("utf-8")));
        setRequestProperty("Authorization", String.format("Basic %s", encoding));

    }

    //==========================================================================
    /**
     * close connection.
     *
     * @throws java.io.IOException in case of error
     */
    public final void closeConnection() throws Exception {

        try {

            disconnectURL();
            closeOutputStream(outputStream);
            closeInputStream(inputStream);
            closeBufferedReader(bufferedReader);

        } catch (Exception e) {
            throw e;
        }

    } // end closeConnection

    //==========================================================================
    /**
     * disconnect from URL.
     *
     * @throws java.lang.Exception
     */
    protected final void disconnectURL() throws Exception {

        try {

            if (hurlc == null) {
                return;
            }

            String host = hurlc.getURL().getHost();

            if (host == null || host.length() < 1) {
                throw new IOException("host is null or empty");
            }

            hurlc.disconnect();

        } catch (Exception e) {
            throw e;
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

        parameters.entrySet().stream().forEach((entrySet) -> {
            String key = entrySet.getKey();
            Object value = entrySet.getValue();
            
            if(key == null || key.equals("")){
                throw new EmptyStackException();
            }
            
            sb.append(key).append("=").append(value).append("&");
        });

        return sb.toString();

    }

    //==========================================================================
    public class RestfulClientException extends Exception {

        public RestfulClientException() {
            super();
        }

    }

}
