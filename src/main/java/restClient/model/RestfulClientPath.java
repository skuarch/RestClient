package restClient.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import static restClient.model.RestfulClient.HTTP_OK;
import static restClient.model.RestfulClient.HURLC_IS_NULL;
import static restClient.model.RestfulClient.UTF_8;

/**
 *
 * @author skuarch
 */
public class RestfulClientPath extends RestfulClient {

    private InputStream inputStream = null;
    private BufferedReader bufferedReader = null;
    private HttpURLConnection hurlc = null;
    private InputStreamReader inputStreamReader = null;

    //==========================================================================
    /**
     * constructor.
     */
    public RestfulClientPath() {
    }

    //==========================================================================
    /**
     * open connection.
     *
     * @param path String <code>url</code>
     * @throws IOException in case of error.
     */
    protected void openConnection(String path) throws IOException {
        super.openConnection(path, RestfulClient.GET);
    }

    //==========================================================================
    /**
     * write text to web server.
     *
     * @param text String
     * @throws Exception in case of error.
     */
    @Override
    public void sendText(String text) throws Exception {

        this.hurlc = getHurlc();

        if (text == null || text.length() < 1) {
            throw new NullPointerException("text is null");
        }

        if (hurlc == null) {
            throw new IOException(HURLC_IS_NULL);
        }

        try {

            int code;
            inputStream = hurlc.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, Charset.forName(UTF_8));
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }

            code = getResponseCode();
            if (code != HTTP_OK && code != HTTP_CREATED && code != HTTP_ACCEPTED) {
                System.err.println("[WARNING] Server returned HTTP response code: "
                        + code
                        + " for URL: "
                        + getUrl());
            }

        } catch (Exception e) {
            close();
            throw e;
        }

    }

    //==========================================================================
    /**
     * receive text from web service.
     *
     * @return String
     * @throws Exception in case of error
     */
    @Override
    public String receiveText() throws Exception {

        this.hurlc = getHurlc();

        if (hurlc == null) {
            throw new IOException(HURLC_IS_NULL);
        }

        String tmp;
        StringBuilder stringBuilder = new StringBuilder();

        try {

            inputStream = hurlc.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, Charset.forName(UTF_8));
            bufferedReader = new BufferedReader(inputStreamReader);

            while ((tmp = bufferedReader.readLine()) != null) {
                stringBuilder.append(tmp);
            }

        } catch (Exception e) {
            close();
            throw e;
        }

        return stringBuilder.toString();

    }

    //==========================================================================
    @Override
    public void close() throws Exception {
        disconnectURL();
        closeInputStream(inputStream);
        closeInputStreamReader(inputStreamReader);
        closeBufferedReader(bufferedReader);
        isConnected(false);
    }

}
