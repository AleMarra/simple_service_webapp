package parameterized;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;


@RunWith(Parameterized.class)
public class SecurityServiceTest extends RESTApiTestCase{

	/*
	 * Test data generator.
	 * This method is called the the JUnit parameterized test runner and
	 * returns a Collection of Arrays.  For each Array in the Collection,
	 * each array element corresponds to a parameter in the constructor.
	 */
	@Parameters
	public static Collection<Object[]> generateData()
	{
		LinkedHashMap<String, String> urlParams = new LinkedHashMap<String, String>();
		urlParams.put("id", "1");

		LinkedHashMap<String, String> queryParams = new LinkedHashMap<String, String>();
		queryParams.put("keepLogged", "false");
		
		MultivaluedMap<String, String> formEntity = new MultivaluedHashMap<String, String>();
		//{apellido=[a], nombre=[a], username=[a], email=[sdasd], fechaNac=[2013-01-01], padron=[11], rol=[1], password=[a]}
		formEntity.addFirst("username", "test");
		formEntity.addFirst("password", "test");
	
		ArrayList<Step> firstRun = new ArrayList<Step>();
		firstRun.add(new Step("POST",
							"login",
							MediaType.APPLICATION_JSON,
							formEntity,
							new LinkedHashMap<String, String>(),
							new LinkedHashMap<String, String>(),
							200,
							"{\"API\": \"login working\"}"));
		
		ArrayList<Step> secondRun = new ArrayList<Step>();
		secondRun.add(new Step("POST",
							"login",
							MediaType.APPLICATION_JSON,
							formEntity,
							null,
							null,
							200,
							"{\"API\": \"login working\"}"));

		
		ArrayList<Step> thirdRun = new ArrayList<Step>();
		thirdRun.add(new Step("POST",
							"logout",
							MediaType.APPLICATION_JSON,
							formEntity,
							new LinkedHashMap<String, String>(),
							new LinkedHashMap<String, String>(),
							200,
							"{\"API\": \"logout working\"}"));
				
		return Arrays.asList(new Object[][]{
				{firstRun},
				{secondRun},
				{thirdRun}
			  });

	}
	
	/*
	 * Called once for every @Parameter in generateData result list 	
	 */
	public SecurityServiceTest(ArrayList<Step> steps){
		this.steps = steps;
	}
	
	
	@Before
	public void setUp() throws Exception {
		// Called once per step
		setBaseTarget("http://localhost:8080/simple-service-webapp/api/");
	}
	
	@Test
	public void testRequestReturnsAsExpected() {
		Iterator<Step> it =  steps.iterator();
		
		while (it.hasNext()){
			Step step = it.next();
			
			System.out.println(step.toString());
			
			webTarget = webTarget.path(step.actionUrl);
			
			if (step.urlParams != null){
				for(Entry<String, String> requiredParam: step.urlParams.entrySet()){
					// Adds url params in the form of /key/value
					// LinkedHashMap guarantees iteration order to be the same as input order
					System.out.println(requiredParam.toString());
					webTarget = webTarget.path(requiredParam.getKey())
							 .path(requiredParam.getValue());
				}
			}
			
			if (step.queryParams != null){
				for(Entry<String, String> optionalParam: step.queryParams.entrySet()){
					// Adds query params in the form of ?key=value
					System.out.println(optionalParam.toString());
					webTarget = webTarget.queryParam(optionalParam.getKey(), optionalParam.getValue());
				}
			}
			
			
			switch (step.actionType){
			case "GET": 
						response = webTarget.request(step.mediaType).get();
						break;
						
			case "POST":
						response = webTarget.request(step.mediaType).post(Entity.form(step.formEntity));
						break;
						
			case "PUT": 
						break;
						
			case "DELETE": 
						break;
			}			
			
			
			System.out.println(webTarget.getUri().toString());
		
			
			assertTrue("Expected " +step.status+", got "+response.toString(),response.getStatus() == step.status);
			
			
			
			assertTrue("Dummy test: " + step.actionType + step.actionUrl, true);
		}
	}
}
