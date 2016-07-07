package restClient.model;

import restClient.net.HttpClientBody;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author skuarch
 */
@Ignore
public class PostRequestTest {

    private static final String URL = "http://localhost:8000";
    private static final String CREDENTIALS = "skuarch@yahoo.com.mx:59119d7e886176032c2f0306e4101a7f";

    //==========================================================================
    /**
     * testing send post.
     *
     * @throws Exception
     */
    @Test
    public void sendRequestPost() throws Exception {

        System.out.println("PostRequestTest ---> send post");

        HttpClientBody wscb = new HttpClientBody(
                URL + "/posts",
                RestfulClient.POST,
                URL);

        try {

            wscb.openConnection();
            wscb.sendText("title='title'&body='body'&userId=1");
            wscb.receiveText();
            //System.out.println("response code " + );

        } catch (Exception e) {
            throw e;
        } finally {
            wscb.close();
        }

    }

}
