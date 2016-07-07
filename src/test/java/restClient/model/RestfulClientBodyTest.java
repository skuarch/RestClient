package restClient.model;

import java.io.IOException;
import java.net.ConnectException;
import org.junit.Test;

/**
 *
 * @author skuarch
 */
public class RestfulClientBodyTest {

    public static final String CREDENTIALS = "";
    public static final String URL = "http://localhost:8000/";

    //==========================================================================
    public RestfulClientBodyTest() {
    }

    //==========================================================================
    /**
     * POST.
     */
    @Test
    public void testSendText() throws Exception {

        System.out.println("RestfulClientBodyTest ---> POST");
        RestfulClientBody instance = new RestfulClientBody();

        try {

            //wrong request method
            String text = "testing";
            instance.openConnection(URL, "POST");
            instance.setAuthentication(CREDENTIALS);
            instance.sendText(text);

        } catch (Exception e) {

            if (!(e instanceof ConnectException)) {
                throw e;
            }

        } finally {
            instance.close();
        }

    }

    //==========================================================================
    /**
     * POST.
     */
    @Test
    public void testSendText1() throws Exception {

        System.out.println("RestfulClientBodyTest ---> PUT");
        RestfulClientBody instance = new RestfulClientBody();

        try {

            //wrong url and wrong request method
            String text = "testing";
            instance.openConnection(URL + "v1/thisurldoesntexits", "PUT");
            instance.setDefaulProperties();
            instance.setAuthentication(CREDENTIALS);
            instance.sendText(text);

        } catch (Exception e) {
            if (!(e instanceof ConnectException)) {
                throw e;
            }
        } finally {
            instance.close();
        }

    }

    //==========================================================================
    /**
     * Test of receiveText method, of class RestfulClientBody.
     */
    @Test
    public void testReceiveText() throws Exception {
        System.out.println("RestfulClientBodyTest ---> receiveText");

        RestfulClientBody instance = new RestfulClientBody();

        try {

            // incomplete request or wrong parameters
            String parameters = "name='mocos10'&urlStreaming='mas mocos1'&genre.id=1&country.id=1&stationType.id=1&keyword.id=1&language.id=1&description='mcoos'&active=1";
            instance.openConnection(URL + "v1/station/create", "POST");
            instance.setAuthentication(CREDENTIALS);
            instance.sendText(parameters);
            String result = instance.receiveText();
            System.out.println("result: " + result);

        } catch (Exception e) {

            if (!(e instanceof IOException)) {
                throw e;
            }

        } finally {
            instance.close();
        }
    }

    //==========================================================================
    /**
     * Test of receiveText method, of class RestfulClientBody.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void correctImplementation() throws Exception {
        System.out.println("RestfulClientBodyTest ---> correctImplementation");

        RestfulClientBody instance = new RestfulClientBody();

        try {

            // correct implementation, wrong request method
            String parameters = "name='mocos10'&urlStreaming='mas mocos1'&genre.id=1&country.id=1&stationType.id=1&keyword.id=1&language.id=1&description='mcoos'&active=1";
            instance.openConnection("http://google.com", "POST");
            instance.setAuthentication(CREDENTIALS);
            instance.sendText(parameters);
            instance.getResponseCodeCache();
            String result = instance.receiveText();
            System.out.println("result: " + result);

        } catch (Exception e) {

            if (!(e instanceof IOException)) {
                throw e;
            }

        } finally {
            instance.close();
        }
    }

}
