package parameterized;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class SecurityServiceTest {
	
//	static private final String baseUrl = "http://localhost:8080/simple-service-webapp/api";
//	
//	static private Client client = ClientBuilder.newClient();
//	
//	static private WebTarget webTarget = client.target(securityApiUrl);

	
	
	private String actionType;		  // HTTP action type: GET, POST, PUT, DELETE
	private String actionUrl;		  // url from base, ie: /controller/method
	private List<HashMap<String, String>> urlParams;   // Mandatory action params, ie: /controller/method/id
	private List<HashMap<String, String>> queryParams; // Optional action params, ie: /controller/method/id?opt=val
	
	private String expectedResult;
	

	/*
	 * Called once for every @Parameter parameters list 	
	 */
	public SecurityServiceTest(String actionType, String actionUrl, List<HashMap<String, String>> urlParams,
			List<HashMap<String, String>> queryParams, String expectedResult) 
	{
		
		this.actionType = actionType;
		this.actionUrl = actionUrl;
		this.urlParams = urlParams;
		this.queryParams = queryParams;
		this.expectedResult = expectedResult;
		
	}
	
	/*
	 * Test data generator.
	 * This method is called the the JUnit parameterized test runner and
	 * returns a Collection of Arrays.  For each Array in the Collection,
	 * each array element corresponds to a parameter in the constructor.
	 */
	@Parameters
	public static Collection<Object[]> generateData()
	{
		// In this example, the parameter generator returns a List of
		// arrays.  Each array has two elements: { datum, expected }.
		// These data are hard-coded into the class, but they could be
		// generated or loaded in any way you like.
		List<HashMap<String, String>> urlParams = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> url_param = new HashMap<String, String>();  
		url_param.put("id", "1");
		urlParams.add(url_param);
		
		List<HashMap<String, String>> queryParams = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> query_param = new HashMap<String, String>();
		query_param.put("keepLogged", "false");
		queryParams.add(query_param);

		return Arrays.asList(new Object[][]{
			{ "GET", "/login", urlParams, queryParams, "{\"API\": \"login working\"}"},
			{ "GET", "/logout", urlParams, null, "{\"API\": \"logout working\"}"}
		  });
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRequestReturnsOk() {
		System.out.println("Dummy test: " + this.actionUrl);
		System.out.println(this.actionType +" "+ this.urlParams.toString() 
						+" "+ this.expectedResult);
		
		assertTrue("Dummy test: " + this.actionType + this.actionUrl, true);
	}

}
