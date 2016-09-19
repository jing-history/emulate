package jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by wangyunjing on 16/9/18.
 */
@Path("mysource")
public class MySource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(){
        return "Got it!";
    }
}
