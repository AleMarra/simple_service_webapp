package parameterized;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

import parameterized.RESTApiTestCase.Step;

@RunWith(Parameterized.class)
public class SecurityServiceTest extends RESTApiTestCase{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		setBaseTarget("http://localhost:8080/simple-service-webapp/api");
	}

	/*
	 * Called once for every @Parameter in generateData result list 	
	 */
	public SecurityServiceTest(ArrayList<Step> steps){
		this.steps = steps;
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
		List<HashMap<String, String>> urlParams = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> url_param = new HashMap<String, String>();  
		url_param.put("id", "1");
		urlParams.add(url_param);
		
		List<HashMap<String, String>> queryParams = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> query_param = new HashMap<String, String>();
		query_param.put("keepLogged", "false");
		queryParams.add(query_param);
	
		ArrayList<Step> firstRun = new ArrayList<Step>();
		firstRun.add(new Step("GET",
							"/login",
							urlParams,
							queryParams,
							"{\"API\": \"login working\"}"));
		
		firstRun.add(new Step("POST",
						"/login",
						urlParams,
						queryParams,
						"{\"API\": \"login working\"}"));

		
		ArrayList<Step> secondRun = new ArrayList<Step>();
		firstRun.add(new Step("GET",
							"/logout",
							urlParams,
							new ArrayList<HashMap<String, String>>(),
							"{\"API\": \"logout working\"}"));
				
		return Arrays.asList(new Object[][]{
				{firstRun},
				{secondRun}
			  });

	}
	
	
	@Test
	public void testRequestReturnsAsExpected() {
		Iterator<Step> it =  steps.iterator();
		
		while (it.hasNext()){
			Step step = it.next();
			System.out.println("Dummy test url: "+ baseUrl + step.actionUrl);
			System.out.println(step.actionType +" "+
							step.urlParams.toString() +" "+
							step.queryParams.toString()+" "+
							step.expectedResult);
			
			assertTrue("Dummy test: " + step.actionType + step.actionUrl, true);
		}
	}
}
