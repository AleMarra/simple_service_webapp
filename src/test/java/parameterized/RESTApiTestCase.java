package parameterized;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class RESTApiTestCase {
	
	static protected Client client = ClientBuilder.newClient();
	static protected String baseUrl;
	static protected WebTarget webTarget;
	
	static protected void setBaseTarget(String url){
		baseUrl = url;
		webTarget = client.target(baseUrl);
	}
	
	static protected class Step{
		public String actionType;		  // HTTP action type: GET, POST, PUT, DELETE
		public String actionUrl;		  // url from base, ie: /controller/method
		public List<HashMap<String, String>> urlParams;   // Mandatory action params, ie: /controller/method/id
		public List<HashMap<String, String>> queryParams; // Optional action params, ie: /controller/method/id?opt=val
		
		public String expectedResult;
	
		public Step(String actionType, String actionUrl,
				List<HashMap<String, String>> urlParams,
				List<HashMap<String, String>> queryParams,
				String expectedResult) 
		{
		
		this.actionType = actionType;
		this.actionUrl = actionUrl;
		this.urlParams = urlParams;
		this.queryParams = queryParams;
		this.expectedResult = expectedResult;
		
		}
	}
	
	protected List<Step> steps;
	
	
}
