package tk.jingzing.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangyunjing on 16/9/18.
 */
public class MySourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setup() throws Exception{
        server = Main.startServer();

        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown()throws Exception{
        server.stop();
    }

    @Test
    public void testGetIt(){
        String resposeMsg = target.path("mysource").request().get(String.class);
        assertEquals("Got it!",resposeMsg);
    }
}
