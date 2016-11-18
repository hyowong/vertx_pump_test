import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.junit.Test;

public class GetRequest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void getTest(){

        for(int i=0;i<10;i++){
            ClientResponse response = doGet();

            logger.info(response.getStatus()+" "+response.getEntity(String.class));

        }

    }

    private ClientResponse doGet() {

        Client client= Client.create();

        WebResource resource=client.resource("http://localhost:8080/abc");

        ClientResponse response=resource.get(ClientResponse.class);

        return response;


    }
}
