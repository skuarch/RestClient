### Simple Http client for rest services
If you are looking for something lightweight and only send/receive text this library is the best option for you.

> no dependencies

> easy to use

> lightweight

> only pure java


This library sends text to restful webservice, just used the wrappers and thats all.

#### What doesn't this library do?

* no attachments
* no marshall
* no include the format of the request
* no patch request
 
#### how to use it?
##### java 6
```java
HttpClientBody hcb = new HttpClientBody("http://jsonplaceholder.typicode.com/posts/1", "DELETE");

        try {

            hcb.openConnection();
            String parameters = "id=1&title='title'&body='body'&userId=1";
            hcb.sendText(parameters);
            String result = hcb.receiveText();
            System.out.println("result3 " + result);
            System.out.println("response code " + hcb.getResponceCode());

        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                hcb.close();
            } catch (Exception e) {
                throw e;
            }
        }
```
##### java 7+
```java
try (HttpClientBody hcb = new HttpClientBody("http://jsonplaceholder.typicode.com/posts/1", "PUT")) {
            hcb.openConnection();
            String parameters = "id=1&title='title'&body='body'&userId=1";
            hcb.sendText(parameters);
            String r = hcb.receiveText();
            System.out.println("result2 " + r);
            System.out.println("response code " + hcb.getResponceCode());
}
```

License
----

GPL 3.0


**Free Software, Hell Yeah!**
