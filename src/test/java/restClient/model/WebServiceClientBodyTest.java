package restClient.model;

import restClient.net.HttpClientBody;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author skuarch
 */
public class WebServiceClientBodyTest {

    public static final String CREDENTIALS = "";

    //==========================================================================
    public WebServiceClientBodyTest() {
    }

    //==========================================================================
    /**
     * Test of sendText method, of class HttpClientBody.
     */
    @Test
    @Ignore
    public void testSendText() throws Exception {
        System.out.println("WebServiceClientBodyTest ---> sendText");
        String text = "mocos";

        //parameters are incorrect
        try {
            HttpClientBody instance
                    = new HttpClientBody(
                            "http://localhost:8000/v1/station/create",
                            "POST",
                            CREDENTIALS
                    );
            instance.openConnection();
            instance.sendText(text);
            String result = instance.receiveText();
            System.out.println("result " + result);
        } catch (Exception e) {
            //if (!(e instanceof ConnectException)) {
            throw e;
            //}
        }

    }

    //==========================================================================
    /**
     * Test of sendText method, of class HttpClientBody.
     */
    @Test
    public void testSendText2() throws Exception {
        System.out.println("WebServiceClientBodyTest ---> sendText");
        String text = "mocos";

        //wrong request method, get is not allowed
        try {
            HttpClientBody instance
                    = new HttpClientBody(
                            "http://localhost:8000/v1/station/create",
                            "GET",
                            CREDENTIALS
                    );
            instance.openConnection();
            instance.sendText(text);
            //String expectedResult = instance.receiveText();

        } catch (Exception e) {
            if (!(e instanceof UnsupportedOperationException)) {
                throw e;
            }
        }

    }

}
