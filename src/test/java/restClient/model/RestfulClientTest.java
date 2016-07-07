package restClient.model;

import restClient.model.RestfulClient;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.UnknownHostException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author skuarch
 */
public class RestfulClientTest {

    //==========================================================================    
    public RestfulClientTest() {
    }

    //==========================================================================    
    @Test
    public void testOpenConnection1() throws Exception {

        System.out.println("RestfulClientTest ---> openConnection1");
        Exception ex = null;
        String path = "escucharadio.com.mx"; //wrong url http is missing
        String requestMethod = "get"; // wrong method
        RestfulClient instance = new RestfulClientImpl();

        try {

            instance.openConnection(path, requestMethod);

        } catch (Exception e) {
            ex = e;
        }

        if (ex == null) {
            throw new Exception("the url is wrong and the exception is not thrown");
        }

    }

    //==========================================================================
    @Test
    public void testOpenConnection2() throws Exception {

        System.out.println("RestfulClientTest ---> openConnection2");
        String path = "google.com"; //wrong url http is missing
        String requestMethod = "get"; // wrong method
        RestfulClient instance = new RestfulClientImpl();

        try {

            instance.openConnection(path, requestMethod);

        } catch (Exception e) {

            if (!(e instanceof MalformedURLException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testOpenConnection3() throws Exception {

        System.out.println("RestfulClientTest ---> openConnection3");
        String path = "http://google.com";
        String requestMethod = "get"; //wrong method, GET should be upper case
        RestfulClient instance = new RestfulClientImpl();

        try {

            instance.openConnection(path, requestMethod);

        } catch (Exception e) {

            if (!(e instanceof ProtocolException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testOpenConnection4() throws Exception {

        // this test should be pass
        System.out.println("RestfulClientTest ---> openConnection4");
        String path = "http://google.com";
        String requestMethod = "GET";
        RestfulClient instance = new RestfulClientImpl();

        try {

            instance.openConnection(path, requestMethod);

        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testOpenConnection5() throws Exception {

        System.out.println("RestfulClientTest ---> openConnection5");
        String path = ""; // empty
        String requestMethod = ""; // empty
        RestfulClient instance = new RestfulClientImpl();

        try {

            instance.openConnection(path, requestMethod);

        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testOpenConnection6() throws Exception {

        System.out.println("RestfulClientTest ---> openConnection6");
        RestfulClient instance = new RestfulClientImpl();
        String path = null; // incorrect
        String requestMethod = null; // incorrect

        try {

            instance.openConnection(path, requestMethod);

        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testOpenConnection7() throws Exception {

        System.out.println("RestfulClientTest ---> openConnection7");
        String path = "http://google.comx"; //wrong url
        String requestMethod = "GET";
        RestfulClient instance = new RestfulClientImpl();

        try {

            instance.openConnection(path, requestMethod);
            instance.getResponseCode();

        } catch (Exception e) {

            if (e instanceof UnknownHostException) {
                return;
            } else {
                throw e;
            }

        }

        Assert.fail("the url http://google.comx doesn't exists and the openConnetion didn't throw any exception");

    }

    //==========================================================================
    @Test
    public void testOpenConnection8() throws Exception {

        System.out.println("RestfulClientTest ---> openConnection8");
        String path = "http://google.com"; //wrong url
        String requestMethod = "SOMETHING";
        RestfulClient instance = new RestfulClientImpl();

        try {

            instance.openConnection(path, requestMethod);
            instance.getResponseCode();

        } catch (Exception e) {

            if (e instanceof ProtocolException) {
                return;
            } else {
                throw e;
            }

        }

        Assert.fail("the method 'SOMETHING' doesn't exists and the openConnetion didn't throw any exception");

    }

    //==========================================================================
    @Test
    public void testSetRequestProperty1() throws Exception {

        System.out.println("RestfulClientTest ---> setRequestProperty1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.setRequestProperty("", ""); // wrong
        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testSetRequestProperty2() throws Exception {

        System.out.println("RestfulClientTest ---> setRequestProperty2");
        RestfulClient instance = new RestfulClientImpl();

        try {
            //connection is not open
            instance.setRequestProperty("popo", "popo");
        } catch (Exception e) {

            if (!(e instanceof IllegalStateException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testSetRequestProperty3() throws Exception {

        System.out.println("RestfulClientTest ---> setRequestProperty3");
        RestfulClient instance = new RestfulClientImpl();

        try {
            //connection is not open and the parameters are wrong
            instance.setRequestProperty(null, null);
        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testSetRequestProperty4() throws Exception {

        System.out.println("RestfulClientTest ---> setRequestProperty4");
        RestfulClient instance = new RestfulClientImpl();

        try {
            // this is ok
            instance.openConnection("http://google.com", "GET");
            instance.setRequestProperty("hello", "hello");
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testSetAuthentication1() throws Exception {

        System.out.println("RestfulClientTest ---> testSetAuthentication1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            //wrong credentials
            instance.setAuthentication(null);
        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testSetAuthentication2() throws Exception {

        System.out.println("RestfulClientTest ---> testSetAuthentication2");
        RestfulClient instance = new RestfulClientImpl();

        try {
            //wrong credentials
            instance.setAuthentication("");
        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testSetAuthentication3() throws Exception {

        System.out.println("RestfulClientTest ---> testSetAuthentication3");
        RestfulClient instance = new RestfulClientImpl();

        try {
            // connection is not open
            instance.setAuthentication("something");
        } catch (Exception e) {

            if (!(e instanceof IllegalStateException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testSetAuthentication4() throws Exception {

        System.out.println("RestfulClientTest ---> testSetAuthentication4");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.openConnection("http://google.com", "GET");
            instance.setAuthentication("something");
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testCloseConnection1() throws Exception {

        System.out.println("RestfulClientTest ---> testCloseConnection1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.close();
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testDisconnectURL1() throws Exception {

        System.out.println("RestfulClientTest ---> testDisconnectURL1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.disconnectURL();
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testGetQueryParameters1() throws Exception {

        System.out.println("RestfulClientTest ---> testGetQueryParameters1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.getQueryParameters(null);
        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testGetQueryParameters2() throws Exception {

        System.out.println("RestfulClientTest ---> testGetQueryParameters2");
        RestfulClient instance = new RestfulClientImpl();

        try {
            //empty map
            Map map = new HashMap();
            instance.getQueryParameters(map);
        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

            return;

        }

        throw new Exception("map is empty and the exception is not thrown");

    }

    //==========================================================================
    @Test
    public void testGetQueryParameters3() throws Exception {

        System.out.println("RestfulClientTest ---> testGetQueryParameters3");
        RestfulClient instance = new RestfulClientImpl();

        try {
            Map map = new HashMap();
            map.put("hello", "hello");
            String r = instance.getQueryParameters(map);
            Assert.assertEquals("hello=hello&", r);
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testGetQueryParameters4() throws Exception {

        System.out.println("RestfulClientTest ---> testGetQueryParameters4");
        RestfulClient instance = new RestfulClientImpl();

        try {
            Map map = new HashMap();
            map.put("", "");
            String r = instance.getQueryParameters(map);
            Assert.assertEquals("hello=hello&", r);
        } catch (Exception e) {
            if (e instanceof EmptyStackException) {
                return;
            } else {
                throw e;
            }

        }

        Assert.fail("the map key and value are empty and any exception was throw");

    }

    //==========================================================================
    @Test
    public void testSetDefaulProperties1() throws Exception {

        System.out.println("RestfulClientTest ---> testSetDefaulProperties1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            //connection is not open
            instance.setDefaulProperties();
        } catch (Exception e) {
            if (e instanceof IllegalStateException) {
                return;
            } else {
                throw e;
            }

        }

        Assert.fail("the connection is not established, but the method doesn't throw any exception");

    }

    //==========================================================================
    @Test
    public void testSetDefaulProperties2() throws Exception {

        System.out.println("RestfulClientTest ---> testSetDefaulProperties2");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.openConnection("http://google.com", "GET");
            instance.setDefaulProperties();
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testGetResponse1() throws Exception {

        System.out.println("RestfulClientTest ---> testGetResponse1");

        RestfulClient instance = new RestfulClientImpl();

        try {

            //get the response code before open connection
            instance.getResponseCode();

        } catch (Exception e) {

            if (!(e instanceof ConnectException)) {
                throw e;
            }
        }

    }

    //==========================================================================
    @Test
    public void testGetResponse2() throws Exception {

        System.out.println("RestfulClientTest ---> testGetResponse2");

        RestfulClient instance = new RestfulClientImpl();

        try {

            //get the response code before open connection
            instance.openConnection("http://google.com", "GET");
            instance.getResponseCode();

        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    public class RestfulClientImpl extends RestfulClient {

        public void sendText(String text) throws IOException {
        }

        public String receiveText() throws IOException {
            return "";
        }

        @Override
        public void close() throws Exception {            
        }
    }

}
