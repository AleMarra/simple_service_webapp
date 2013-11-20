package com.fiuba.taller.service;

import com.fiuba.taller.service.requests.LoginRequest;
import com.fiuba.taller.service.requests.RegisterUserRequest;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SecurityService {

    static private String securityApiUrl = "http://localhost:8080/simple-service-webapp/api/mocks/";
    static private Client client = ClientBuilder.newClient();
    static private WebTarget webTarget = client.target(securityApiUrl);

 
    @POST
    @Path("registeruser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(RegisterUserRequest request) {
        System.out.println(request);
        WebTarget resourceWebTarget = webTarget.path("registeruser");
        
        Response response = resourceWebTarget
				                .request(MediaType.APPLICATION_XML_TYPE)
				                .post(Entity.form(request.toForm()));
      
        System.out.println(request.toString());
        
        response.bufferEntity();

        System.out.println(response.toString());
        
        SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);

        System.out.println(securityResponse.toString());

        if(securityResponse.getSuccess()){
            return Response
                    .ok()
                    .entity(securityResponse)
                    .build();
        }else{
            return Response
                    .status(response.getStatus())
                    .entity(securityResponse)
                    .build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request) {
    	
        WebTarget resourceWebTarget = webTarget.path("login");
        Response response = resourceWebTarget
                .request(MediaType.APPLICATION_XML_TYPE)
                .post(Entity.form(request.toForm()));

        System.out.println(request.toString());
        
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
    public String activateUser() {
        return "{\"API\": \"activateUser working\"}";
    }

    @POST
    @Path("changepassword")
    public String changePassword() {
        return "{\"API\": \"changePassword working\"}";
    }

    @POST
    @Path("resetpassword")
    public String resetPassword() {
        return "{\"API\": \"resetpassword working\"}";
    }

    @POST
    @Path("disableaccount")
    public String disableAccount() {
        return "{\"API\": \"disableAccount working\"}";
    }

    @POST
    @Path("enableaccount")
    public String enableAccount() {
        return "{\"API\": \"enableAccount working\"}";
    }

    @POST
    @Path("enableaccountfromemaill")
    public String enableAccountFromEmaill() {
        return "{\"API\": \"enableAccountFromEmaill working\"}";
    }

}