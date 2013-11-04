package com.fiuba.taller.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SecurityService {

    static private String securityApiUrl = "http://localhost:8080/simple-service-webapp/api/mocks";
    static private Client client = ClientBuilder.newClient();
    static private WebTarget webTarget = client.target(securityApiUrl);


    @POST
    @Path("registeruser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String registerUser(MultivaluedMap<String, String> formParams) {
    	return (formParams.toString());
//    	System.out.println(formParams);
//        WebTarget resourceWebTarget = webTarget.path("registeruser");
//        Response response = resourceWebTarget
//                .request(MediaType.APPLICATION_XML)
//                .post(Entity.form(formParams));
//
//        System.out.println(response.getStatus());
//        System.out.println(response.readEntity(String.class));
//        no me sale parsear la puta respuesta
//        System.out.println(response.readEntity(SecurityResponse.class));
//        SecurityResponse parsedResponseBody = response.readEntity(SecurityResponse.class);
//        System.out.println(parsedResponseBody);
//        System.out.println(parsedResponseBody.isSuccessful());
//        System.out.println(parsedResponseBody.getReason());
//        return "{\"API\": \"registerUser working\"}";
    }

    @POST
    @Path("login")
    public String login() {
        return "{\"API\": \"login working\"}";
    }

    @POST
    @Path("logout")
    public String logout() {
        return "{\"API\": \"logout working\"}";
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


    @GET
    @Path("testRedirect")
    @Produces(MediaType.APPLICATION_JSON)
    public String callWs() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://api.despegar.com/cities/tripplanning?includecity=true");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if ( entity == null ){
                ResponseBuilder builder = Response.status(Response.Status.NO_CONTENT);
                builder.entity("Nothing found in despegar.com");
                Response error = builder.build();

                throw new WebApplicationException(error);
            }

            String entityStr = EntityUtils.toString(entity);

            if ( entityStr.contains("exceeded the daily limit") ){

                ResponseBuilder builder = Response.status(Response.Status.FORBIDDEN);
                builder.entity("Daily limit of requests exceeded");
                Response error = builder.build();

                throw new WebApplicationException(error);
            }

            return entityStr;

        } catch (IOException e) {
            e.printStackTrace();

            ResponseBuilder builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            builder.entity("Something went wrong while parsing the response");
            Response error = builder.build();

            throw new WebApplicationException(error);
        }
    }

}