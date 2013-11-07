package com.fiuba.taller.mock_SOAP;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@WebService
public interface SecurityMockSOAP {

	@WebMethod
	public Response registerUser(MultivaluedMap<String, String> formParams);
	
	@WebMethod
	public Response login(@FormParam("username") String username,
			 			   @FormParam("password") String password);
	@WebMethod
	public Response logout(@FormParam("authToken") String authToken);
}
