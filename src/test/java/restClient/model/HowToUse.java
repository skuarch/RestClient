package restClient.model;

import org.junit.Ignore;
import org.junit.Test;
import restClient.net.HttpClientBody;

/**
 *
 * @author skuarch
 */
public class HowToUse {

    //==========================================================================
    @Test
    @Ignore
    public void tryCatchResources() {

        /*try (HttpClientBody wscb1 = new HttpClientBody("http://jsonplaceholder.typicode.com/posts", "POST")) {

            wscb1.openConnection();
            String parameters = "id=1&title='title'&body='body'&userId=1";
            wscb1.sendText(parameters);
            String result = wscb1.receiveText();
            System.out.println("result1 " + result);
            System.out.println("response code " + wscb1.getResponceCode());

        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    //==========================================================================
    @Test
    @Ignore
    public void tryResources() {

        /*try (HttpClientBody wscb2 = new HttpClientBody("http://jsonplaceholder.typicode.com/posts/1", "PUT")) {
            wscb2.openConnection();
            String parameters = "id=1&title='title'&body='body'&userId=1";
            wscb2.sendText(parameters);
            String r = wscb2.receiveText();
            System.out.println("result2 " + r);
            System.out.println("response code " + wscb2.getResponceCode());
        }*/
    }

    //==========================================================================
    @Test
    public void tryCatchFinally() throws Exception {

        HttpClientBody wscb3 = new HttpClientBody("http://jsonplaceholder.typicode.com/posts/1", "DELETE");

        try {

            wscb3.openConnection();
            String parameters = "id=1&title='title'&body='body'&userId=1";
            wscb3.sendText(parameters);
            String result = wscb3.receiveText();
            System.out.println("result3 " + result);
            System.out.println("response code " + wscb3.getResponceCode());

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                wscb3.close();
            } catch (Exception e) {
                throw e;
            }
        }
    }

}
