package test.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import test.pojo.Notification;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class TestUtils {

    ObjectMapper mapper;

    private final String host;

    public TestUtils(String host){

        //Set host
        this.host= host;

        mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Helper method that sends a POST request and returns a Notification object from response
     * @param n Notification object to send
     * @return Notification from response
     * @throws IOException
     */
    public Notification sendPost(Notification n, String type) throws IOException {

        HttpPost request = new HttpPost( host+"/"+type+"/send");

        HttpEntity requestEntity = new StringEntity(
                mapper.writeValueAsString(n),
                "application/json", "UTF-8");

        request.setEntity(requestEntity);


        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        assertTrue(response.getStatusLine().getStatusCode() == 200);

        String json = EntityUtils.toString(response.getEntity());

        return mapper.readValue(json, Notification.class);
    }

    /**
     * Helper method that sends a GET request and returns a list of Notification objects from response
     * @return a list of Notification objects from response
     * @throws IOException
     */
    public List<Notification> sendGet(String type) throws IOException {

        HttpUriRequest request = new HttpGet( host+"/"+type+"/readall");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        assertTrue(httpResponse.getStatusLine().getStatusCode() == 200);

        String json = EntityUtils.toString(httpResponse.getEntity());
        return mapper.readerForListOf(Notification.class).readValue(json);
    }
}
