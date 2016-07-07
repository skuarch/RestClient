package restClient.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;

/**
 *
 * @author skuarch
 */
public class RestfulClientBody extends RestfulClient {

    private OutputStream outputStream = null;
    private InputStream inputStream = null;
    private BufferedReader bufferedReader = null;
    private HttpURLConnection hurlc = null;
    private InputStreamReader inputStreamReader = null;

    //==========================================================================
    /**
     * Constructor.
     */
    public RestfulClientBody() {
    }

    //==========================================================================
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
            hurlc.setDoOutput(true);
            outputStream = hurlc.getOutputStream();
            outputStream.write(text.getBytes(Charset.forName(UTF_8)));
            outputStream.flush();

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
    @Override
    public String receiveText() throws Exception {

        if (hurlc == null) {
            throw new IOException(HURLC_IS_NULL);
        }

        String tmp;
        StringBuilder stringBuilder = new StringBuilder();
        int code = 0;

        try {

            inputStream = hurlc.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, Charset.forName(UTF_8));
            bufferedReader = new BufferedReader(inputStreamReader);

            while ((tmp = bufferedReader.readLine()) != null) {
                stringBuilder.append(tmp);
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

        return stringBuilder.toString();

    }

    //==========================================================================
    @Override
    public void close() throws Exception {
        disconnectURL();
        closeOutputStream(outputStream);
        closeInputStream(inputStream);
        closeInputStreamReader(inputStreamReader);
        closeBufferedReader(bufferedReader);
        isConnected(false);
    }

}
