package model.net.rest;

import java.io.IOException;
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

        System.out.println("openConnection1");
        Exception ex = null;
        String path = "escucharadio.com.mx"; //wrong url
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

        System.out.println("openConnection2");
        String path = "google.com"; //wrong url
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

        System.out.println("openConnection3");
        String path = "http://google.com";
        String requestMethod = "get"; //wrong method
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

        System.out.println("openConnection4");
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

        System.out.println("openConnection5");
        String path = "";
        String requestMethod = "";
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

        System.out.println("openConnection6");
        RestfulClient instance = new RestfulClientImpl();
        String path = null;
        String requestMethod = null;

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

        System.out.println("openConnection7");
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

        Assert.fail("the url http://googlex1.comx doesn't exists and the openConnetion didn't throw any exception");

    }

    //==========================================================================
    @Test
    public void testSetRequestProperty1() throws Exception {

        System.out.println("setRequestProperty1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.setRequestProperty("", "");
        } catch (Exception e) {

            if (!(e instanceof IllegalArgumentException)) {
                throw e;
            }

        }

    }

    //==========================================================================
    @Test
    public void testSetRequestProperty2() throws Exception {

        System.out.println("setRequestProperty2");
        RestfulClient instance = new RestfulClientImpl();

        try {
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

        System.out.println("setRequestProperty3");
        RestfulClient instance = new RestfulClientImpl();

        try {
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

        System.out.println("setRequestProperty4");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.openConnection("http://google.com", "GET");
            instance.setRequestProperty("hello", "hello");
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testSetAuthentication1() throws Exception {

        System.out.println("testSetAuthentication1");
        RestfulClient instance = new RestfulClientImpl();

        try {
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

        System.out.println("testSetAuthentication2");
        RestfulClient instance = new RestfulClientImpl();

        try {
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

        System.out.println("testSetAuthentication3");
        RestfulClient instance = new RestfulClientImpl();

        try {
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

        System.out.println("testSetAuthentication4");
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

        System.out.println("testCloseConnection1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.closeConnection();
        } catch (Exception e) {
            throw e;
        }

    }

    //==========================================================================
    @Test
    public void testDisconnectURL1() throws Exception {

        System.out.println("testDisconnectURL1");
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

        System.out.println("testGetQueryParameters1");
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

        System.out.println("testGetQueryParameters2");
        RestfulClient instance = new RestfulClientImpl();

        try {
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

        System.out.println("testGetQueryParameters3");
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

        System.out.println("testGetQueryParameters4");
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

        Assert.fail("the map key is empty and any exception was throw");
        
    }

    //==========================================================================
    @Test
    public void testSetDefaulProperties1() throws Exception {

        System.out.println("testSetDefaulProperties1");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.setDefaulProperties();
        } catch (Exception e) {
            if (e instanceof IllegalStateException) {
                return;
            } else {
                throw e;
            }

        }

        Assert.fail("the connection is not establish, but the method doesn't throw any exception");

    }

    //==========================================================================
    @Test
    public void testSetDefaulProperties2() throws Exception {

        System.out.println("testSetDefaulProperties2");
        RestfulClient instance = new RestfulClientImpl();

        try {
            instance.openConnection("http://google.com", "GET");
            instance.setDefaulProperties();
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
    }

}
