package restClient.model;

import java.io.IOException;
import java.net.ConnectException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author skuarch
 */
public class RestfulClientPathTest {

    public static final String CREDENTIALS = "";

    //==========================================================================
    public RestfulClientPathTest() {
    }

    //==========================================================================
    /**
     * Test of sendText method, of class RestfulClientPath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void get1() throws Exception {

        //everything is ok
        System.out.println("RestfulClientPathTest ---> get1");
        String expectedResult = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\" /><title>api-radio</title></head><body>welcome to api-radio</body></html>";
        RestfulClientPath instance = new RestfulClientPath();
        instance.openConnection("http://localhost:8000");
        instance.setAuthentication(CREDENTIALS);
        String text = instance.receiveText();
        instance.close();
        assertEquals(expectedResult, text);
    }

    //==========================================================================
    /**
     * Test of sendText method, of class RestfulClientPath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void get2() throws Exception {

        //path is ok and expected result is ok
        System.out.println("RestfulClientPathTest ---> get2");

        try {

            String expectedResult = "{\"status\":\"UP\"}";
            RestfulClientPath instance = new RestfulClientPath();
            instance.openConnection("http://localhost:8000/health", "GET");
            instance.setAuthentication(CREDENTIALS);
            String text = instance.receiveText();
            instance.close();
            assertEquals(expectedResult, text);

        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    /**
     * Test of sendText method, of class RestfulClientPath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void get3() throws Exception {

        //wrong URL        
        System.out.println("RestfulClientPathTest ---> get3");
        RestfulClientPath instance = new RestfulClientPath();
        instance.openConnection("http://localhost:8000/ueueue", "GET");
        instance.setAuthentication(CREDENTIALS);
        int responseCode = instance.getResponseCode();
        instance.close();
        assertEquals(responseCode, 404);
    }

    //==========================================================================
    /**
     * Test of receiveText method, of class RestfulClientPath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void get4() throws Exception {

        //wrong port
        System.out.println("RestfulClientPathTest ---> get4");

        try {

            RestfulClientPath instance = new RestfulClientPath();
            instance.openConnection("http://localhost:10258/ueueue", "GET");
            instance.setAuthentication(CREDENTIALS);

        } catch (Exception e) {

            if (!(e instanceof ConnectException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    /**
     * Test of sendText method, of class RestfulClientPath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void get5() throws Exception {

        //no authentication
        System.out.println("RestfulClientPathTest ---> get5");

        try {

            RestfulClientPath instance = new RestfulClientPath();
            instance.openConnection("http://localhost:8000");
            instance.receiveText();
            int responseCode = instance.getResponseCode();
            instance.close();
            assertEquals(responseCode, 401);

        } catch (Exception e) {

            if (!(e instanceof IOException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    /**
     * Test of sendText method, of class RestfulClientPath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void get6() throws Exception {

        //no authentication
        System.out.println("RestfulClientPathTest ---> get6");
        RestfulClientPath instance = new RestfulClientPath();
        instance.openConnection("http://localhost:8000");
        int responseCode = instance.getResponseCode();
        instance.close();
        assertEquals(responseCode, 401);
    }

    //==========================================================================
    /**
     * Test of sendText method, of class RestfulClientPath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void get7() throws Exception {

        //without connection
        System.out.println("RestfulClientPathTest ---> get7");

        try {

            RestfulClientPath instance = new RestfulClientPath();
            int responseCode = instance.getResponseCode();
            instance.close();
            assertEquals(responseCode, 401);

        } catch (Exception e) {

            if (!(e instanceof ConnectException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    /**
     * Test of sendText method, of class RestfulClientPath.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void get8() throws Exception {

        //wrong port
        System.out.println("RestfulClientPathTest ---> get8");

        try {

            RestfulClientPath instance = new RestfulClientPath();
            instance.openConnection("http://localhost:777777");

        } catch (Exception e) {

            if (!(e instanceof RuntimeException)) {
                throw e;
            }

        }

    }

}
