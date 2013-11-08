package parameterized;

import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;


public class Step{
	public final String actionType;		  // HTTP action type: GET, POST, PUT, DELETE
	public final String actionUrl;		  // url from base, ie: /controller/method
	public final String mediaType;
	public final MultivaluedMap<String, String> formEntity;
	public final Map<String, String> urlParams;   // Mandatory action params, ie: /controller/method/id
	public final Map<String, String> queryParams; // Optional action params, ie: /controller/method/id?opt=val
	public final int status;
	public final String expectedResult;

	public Step(String actionType,
			String actionUrl,
			String mediaType,
			MultivaluedMap<String, String> formEntity,
			Map<String, String> urlParams,
			Map<String, String> queryParams,
			int status,
			String expectedResult) 
	{
	
	this.actionType = actionType;
	this.actionUrl = actionUrl;
	this.mediaType = mediaType;
	this.urlParams = urlParams;
	this.formEntity = formEntity;
	this.queryParams = queryParams;
	this.status = status;
	this.expectedResult = expectedResult;
	
	}
	
	@Override
	public String toString(){
		
		return "Step - "+ "actionType = " + actionType +
						"actionUrl = " + actionUrl +
						"mediaType = " + mediaType +
						"urlParams = " + urlParams +
						"formEntity = " + formEntity+
						"queryParams = " + queryParams+
						"status = " + status +
						"expectedResult = " + expectedResult;
		
	}
}
