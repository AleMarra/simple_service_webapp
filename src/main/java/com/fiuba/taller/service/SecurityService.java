package com.fiuba.taller.service;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SecurityService {

    static private String securityApiUrl = "http://localhost:8080/simple-service-webapp/api/mocks";
    static private Client client = ClientBuilder.newClient();
    static private WebTarget webTarget = client.target(securityApiUrl);


    @POST
    @Path("registeruser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registerUser(MultivaluedMap<String, String> formParams) {
        System.out.println(formParams);
        WebTarget resourceWebTarget = webTarget.path("registeruser");
        
        Response response = resourceWebTarget
				                .request(MediaType.APPLICATION_XML_TYPE)
				                .post(Entity.form(formParams));
        
        response.bufferEntity();

        System.out.println(response.toString());
        
        SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);

        System.out.println(securityResponse.toString());

        if(securityResponse.getSuccess()){
            return Response
                    .ok()
                    .build();
        }else{
            return Response
                    .status(response.getStatus())
                    .entity(securityResponse)
                    .build();
        }
    }
    
    @POST
    @Path("registeruserJSON")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUserJSON(RegisterUserRequest request) {
        System.out.println(request);
        WebTarget resourceWebTarget = webTarget.path("registeruser");
        
//        Response response = resourceWebTarget
//				                .request(MediaType.APPLICATION_XML_TYPE)
//				                .post(Entity.form(formParams));
//        
//        response.bufferEntity();

//        System.out.println(response.toString());
        
//        SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);

        SecurityResponse s = new SecurityResponse(true, request.toString());
        System.out.println(s.toString());

        if(s.getSuccess()){
            return Response
                    .ok()
                    .entity(s)
                    .build();
        }else{
            return Response
                    .ok()
                    .entity(s)
                    .build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(MultivaluedMap<String, String> formParams) {
        WebTarget resourceWebTarget = webTarget.path("login");
        Response response = resourceWebTarget
                .request(MediaType.APPLICATION_XML_TYPE)
                .post(Entity.form(formParams));

        response.bufferEntity();
        
        System.out.println(response.toString());
        
        SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);
        
        if(securityResponse.getSuccess()){
	        return Response
	                .ok()
	                .cookie(new NewCookie("authToken", securityResponse.getAuthToken()))
	                .build();
	    }else{
	    	return Response
	                .status(response.getStatus())
	                .entity(securityResponse)
	                .build();
	    }
    }

    @POST
    @Path("logout")
    public Response logout(@CookieParam("authToken") String authToken) {
        System.out.println("@CookieParam: " + authToken);
        Form form = new Form();
        form.param("authToken", authToken);

        WebTarget resourceWebTarget = webTarget.path("logout");
        Response response = resourceWebTarget
                .request(MediaType.APPLICATION_XML_TYPE)
                .post(Entity.form(form));

        response.bufferEntity();
        
        System.out.println(response.toString());
        
        SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);
        
        if(securityResponse.getSuccess()){
	        return Response
	                .ok()
	                .header("Set-Cookie",
                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
	                .build();
        }else{
        	return Response
	                .status(response.getStatus())
                    .entity(securityResponse)
	                .build();
        }
    }

    @POST
    @Path("isloggedin")
    public Response isLoggedIn(@CookieParam("authToken") String authToken) {
        SecurityResponse securityResponse;

        if (authToken == null) {
            securityResponse = new SecurityResponse(false, "No token");
            return Response
                    .ok()
                    .entity(securityResponse)
                    .build();
        } else {
            System.out.println("@CookieParam: " + authToken);
            Form form = new Form();
            form.param("authToken", authToken);

            WebTarget resourceWebTarget = webTarget.path("isvalidtoken");
            Response response = resourceWebTarget
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .post(Entity.form(form));

            response.bufferEntity();

            System.out.println(response.toString());
        
            securityResponse = response.readEntity(SecurityResponse.class);


            if(securityResponse.getSuccess()){
                return Response
                        .ok()
                        .build();
            } else {
                return Response
                        .status(response.getStatus())
                        .entity(securityResponse)
                        .build();
            }
        }
    }

    @POST
    @Path("activateuser")
    public Response activateUser(MultivaluedMap<String, String> formParams) {
    	SecurityResponse securityResponse;
    	
    	if (formParams == null) {
            securityResponse = new SecurityResponse(false, "Parametros Invalidos: username vacio");
            return Response
                    .ok()
                    .entity(securityResponse)
                    .build();
        } else {
        	WebTarget resourceWebTarget = webTarget.path("activateuser");
            Response response = resourceWebTarget
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .post(Entity.form(formParams));

            response.bufferEntity();

            System.out.println(response.toString());
        
            securityResponse = response.readEntity(SecurityResponse.class);

            if(securityResponse.getSuccess()){
                return Response
                        .ok()
                        .entity(securityResponse)
                        .build();
            } else {
                return Response
                        .status(response.getStatus())
                        .entity(securityResponse)
                        .build();
            }
        }
    }

    @POST
    @Path("changepassword")
    public Response changePassword(MultivaluedMap<String, String> formParams, @CookieParam("authToken") String authToken) {
    	SecurityResponse securityResponse;
    	
    	if (formParams == null) {
            securityResponse = new SecurityResponse(false, "Parametros Invalidos");
            return Response
                    .ok()
                    .entity(securityResponse)
                    .build();
        } else {
        	System.out.println("@CookieParam: " + authToken);
            
        	Form form = new Form();
        	form.param("authToken", authToken);
        	
        	Iterator it = formParams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                String key = (String) pair.getKey();
                List<String> value = (List<String>) pair.getValue();
                form.param(key, value.get(0));
            }
        	
        	WebTarget resourceWebTarget = webTarget.path("changepassword");
            Response response = resourceWebTarget
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .post(Entity.form(form));

            response.bufferEntity();

            System.out.println(response.toString());
        
            securityResponse = response.readEntity(SecurityResponse.class);

            if(securityResponse.getSuccess()){
                return Response
                        .ok()
                        .entity(securityResponse)
                        .build();
            } else {
                return Response
                        .status(response.getStatus())
                        .entity(securityResponse)
                        .build();
            }
        }
    }
    
    @POST
    @Path("resetpassword")
    public Response resetPassword(MultivaluedMap<String, String> formParams, @CookieParam("authToken") String authToken) {
    	SecurityResponse securityResponse;
    	
    	System.out.println("@CookieParam: " + authToken);
            
        Form form = new Form();
        form.param("authToken", authToken);
        	
        if(formParams != null){
        	Iterator it = formParams.entrySet().iterator();
            while (it.hasNext()) {
            	Map.Entry pair = (Map.Entry) it.next();
                String key = (String) pair.getKey();
                List<String> value = (List<String>) pair.getValue();
                form.param(key, value.get(0));
            }
        }
        	
        WebTarget resourceWebTarget = webTarget.path("resetpassword");
        Response response = resourceWebTarget
                .request(MediaType.APPLICATION_XML_TYPE)
                .post(Entity.form(form));

        response.bufferEntity();

        System.out.println(response.toString());
        
        securityResponse = response.readEntity(SecurityResponse.class);

        if(securityResponse.getSuccess()){
        	return Response
        			.ok()
                    .entity(securityResponse)
                    .build();
        } else {
        	return Response
        			.status(response.getStatus())
                    .entity(securityResponse)
                    .build();
        }
    }


    @POST
    @Path("disableaccount")
    public Response disableAccount(MultivaluedMap<String, String> formParams, @CookieParam("authToken") String authToken) {
    	SecurityResponse securityResponse;
    	
    	if (formParams == null) {
            securityResponse = new SecurityResponse(false, "Parametros Invalidos");
            return Response
                    .ok()
                    .entity(securityResponse)
                    .build();
        } else {
        	System.out.println("@CookieParam: " + authToken);
            
        	Form form = new Form();
        	form.param("authToken", authToken);
        	
        	Iterator it = formParams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                String key = (String) pair.getKey();
                List<String> value = (List<String>) pair.getValue();
                form.param(key, value.get(0));
            }
        	
        	WebTarget resourceWebTarget = webTarget.path("disableaccount");
            Response response = resourceWebTarget
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .post(Entity.form(form));

            response.bufferEntity();

            System.out.println(response.toString());
        
            securityResponse = response.readEntity(SecurityResponse.class);

            if(securityResponse.getSuccess()){
                return Response
                        .ok()
                        .entity(securityResponse)
                        .build();
            } else {
                return Response
                        .status(response.getStatus())
                        .entity(securityResponse)
                        .build();
            }
        }
    }

    @POST
    @Path("enableaccount")
    public Response enableAccount(MultivaluedMap<String, String> formParams, @CookieParam("authToken") String authToken) {
    	SecurityResponse securityResponse;
    	
    	if (formParams == null) {
            securityResponse = new SecurityResponse(false, "Parametros Invalidos");
            return Response
                    .ok()
                    .entity(securityResponse)
                    .build();
        } else {
        	System.out.println("@CookieParam: " + authToken);
            
        	Form form = new Form();
        	form.param("authToken", authToken);
        	
        	Iterator it = formParams.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                String key = (String) pair.getKey();
                List<String> value = (List<String>) pair.getValue();
                form.param(key, value.get(0));
            }
        	
        	WebTarget resourceWebTarget = webTarget.path("enableaccount");
            Response response = resourceWebTarget
                    .request(MediaType.APPLICATION_XML_TYPE)
                    .post(Entity.form(form));

            response.bufferEntity();

            System.out.println(response.toString());
        
            securityResponse = response.readEntity(SecurityResponse.class);

            if(securityResponse.getSuccess()){
                return Response
                        .ok()
                        .entity(securityResponse)
                        .build();
            } else {
                return Response
                        .status(response.getStatus())
                        .entity(securityResponse)
                        .build();
            }
        }
    }


    @POST
    @Path("enableaccountfromemaill")
    public String enableAccountFromEmaill() {
        return "{\"API\": \"enableAccountFromEmaill working\"}";
    }
}