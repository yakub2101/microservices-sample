package test.tests;

import org.testng.annotations.Test;
import test.pojo.Notification;
import test.util.TestUtils;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.assertTrue;

/**
 * Simple tests that test email,sms and push notifications microservices.
 * The tests expect services to be running,
 * with gateway service on port 8080 and Eureka running.
 */
public class NotificationsTests {

    /**
     * Default host to which the requests will be sent
     */
    private final String host = "http://localhost:8080";

    TestUtils utils = new TestUtils(host);

    /**
     * Test that sends a GET request to http://localhost:8080/email/readall
     * and verifies the result
     * @throws IOException
     */
    @Test
    public void testGetEmailNotifications() throws IOException {

        sendGetAndVerify("email");
    }

    /**
     * Test that sends a GET request to http://localhost:8080/sms/readall
     * and verifies the result
     * @throws IOException
     */
    @Test
    public void testGetSMSNotifications() throws IOException {

        sendGetAndVerify("sms");
    }

    /**
     * Test that sends a GET request to http://localhost:8080/push/readall
     * and verifies the result
     * @throws IOException
     */
    @Test
    public void testGetPushNotifications() throws IOException {

        sendGetAndVerify("push");
    }

    /**
     * Test that sends a POST request to http://localhost:8080/email/readall
     * and verifies the result
     * @throws IOException
     */
    @Test
    public void testPostEmailNotifications() throws IOException {

        sendPostAndVerify("email");
    }

    /**
     * Test that sends a POST request to http://localhost:8080/sms/readall
     * and verifies the result
     * @throws IOException
     */
    @Test
    public void testPostSMSNotifications() throws IOException {

        sendPostAndVerify("sms");
    }

    /**
     * Test that sends a POST request to http://localhost:8080/push/readall
     * and verifies the result
     * @throws IOException
     */
    @Test
    public void testPostPushNotifications() throws IOException {

        sendPostAndVerify("push");
    }

    /**
     * Helper method that sends a GET request and verifies the result
     * @param type
     * @throws IOException
     */
    private void sendGetAndVerify(String type) throws IOException {

        //Send a notification first to read back
        Notification n1 = new Notification();
        double sortOfRandomID = Math.random();
        n1.setText("Notification " + sortOfRandomID);
        n1.setType(type);
        utils.sendPost(n1,type);

        //Send a GET request
        List<Notification> result = utils.sendGet(type);

        //Java 8 magic!
        Optional<Notification> sentNotification = result.stream().
                filter(n -> n.getType().equals(type)).
                filter(n -> n.getText().equals("Notification " + sortOfRandomID)).
                findFirst();

        assertTrue(sentNotification.isPresent());
    }

    /**
     * Helper method that sends two POST requests and verifies the results
     * @param type
     * @throws IOException
     */
    private void sendPostAndVerify(String type) throws IOException {
        Notification n1 = new Notification();
        n1.setText("Test notification 1");
        n1.setType(type);

        Notification n2 = new Notification();
        n2.setText("Test notification 2");
        n2.setType(type);

        Notification result = utils.sendPost(n1,type);

        assertTrue(result.getText().equals("Test notification 1"));
        assertTrue(result.getType().equals(type));

        result = utils.sendPost(n2,type);

        assertTrue(result.getText().equals("Test notification 2"));
        assertTrue(result.getType().equals(type));
    }
}
