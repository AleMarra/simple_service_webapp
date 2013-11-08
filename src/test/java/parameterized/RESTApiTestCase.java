package parameterized;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class RESTApiTestCase {
	
	protected String baseUrl = "";
	protected WebTarget webTarget = null;
	
	protected Response response = null;
	
	protected void setBaseTarget(String url){
		baseUrl = url;
		webTarget = ClientBuilder.newClient().target(baseUrl);
	}
	
	protected List<Step> steps;
	
	
}
