package com.fiuba.taller.service;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fiuba.taller.mock_SOAP.SoapProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
//import javax.ws.rs.client.Client;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class SecurityService {

//    static private String securityApiUrl = "http://localhost:8080/simple-service-webapp/api/mocks/";
//    static private Client client = ClientBuilder.newClient();
//    static private WebTarget webTarget = client.target(securityApiUrl);
//    
    static private String securityApiUrl_SOAP = "http://localhost:8080/simple-service-webapp/services/SecurityMockSOAP";
//    static private Client client_SOAP = ClientBuilder.newClient();
//    static private WebTarget webTarget_SOAP = client_SOAP.target(securityApiUrl_SOAP);

//
//    @POST
//    @Path("registeruser")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response registerUser(MultivaluedMap<String, String> formParams) {
//        System.out.println(formParams);
//        WebTarget resourceWebTarget = webTarget.path("registeruser");
//        
//        Response response = resourceWebTarget
//				                .request(MediaType.APPLICATION_XML_TYPE)
//				                .post(Entity.form(formParams));
//        
//        response.bufferEntity();
//
//        System.out.println(response.toString());
//        
//        SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);
//
//        System.out.println(securityResponse.toString());
//
//        if(securityResponse.getSuccess()){
//            return Response
//                    .ok()
//                    .build();
//        }else{
//            return Response
//                    .status(response.getStatus())
//                    .entity(securityResponse)
//                    .build();
//        }
//    }
//
//    @POST
//    @Path("login")
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response login(MultivaluedMap<String, String> formParams) {
//        WebTarget resourceWebTarget = webTarget.path("login");
//        Response response = resourceWebTarget
//                .request(MediaType.APPLICATION_XML_TYPE)
//                .post(Entity.form(formParams));
//
//        response.bufferEntity();
//        
//        System.out.println(response.toString());
//        
//        SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);
//        
//        if(securityResponse.getSuccess()){
//	        return Response
//	                .ok()
//	                .cookie(new NewCookie("authToken", securityResponse.getAuthToken()))
//	                .build();
//	    }else{
//	    	return Response
//	                .status(response.getStatus())
//	                .entity(securityResponse)
//	                .build();
//	    }
//    }
//
//    @POST
//    @Path("logout")
//    public Response logout(@CookieParam("authToken") String authToken) {
//        System.out.println("@CookieParam: " + authToken);
//        Form form = new Form();
//        form.param("authToken", authToken);
//
//        WebTarget resourceWebTarget = webTarget.path("logout");
//        Response response = resourceWebTarget
//                .request(MediaType.APPLICATION_XML_TYPE)
//                .post(Entity.form(form));
//
//        response.bufferEntity();
//        
//        System.out.println(response.toString());
//        
//        SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);
//        
//        if(securityResponse.getSuccess()){
//	        return Response
//	                .ok()
//	                .header("Set-Cookie",
//                            "authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
//	                .build();
//        }else{
//        	return Response
//	                .status(response.getStatus())
//                    .entity(securityResponse)
//	                .build();
//        }
//    }
//
//    @POST
//    @Path("isloggedin")
//    public Response isLoggedIn(@CookieParam("authToken") String authToken) {
//        SecurityResponse securityResponse;
//
//        if (authToken == null) {
//            securityResponse = new SecurityResponse(false, "No token");
//            return Response
//                    .ok()
//                    .entity(securityResponse)
//                    .build();
//        } else {
//            System.out.println("@CookieParam: " + authToken);
//            Form form = new Form();
//            form.param("authToken", authToken);
//
//            WebTarget resourceWebTarget = webTarget.path("isvalidtoken");
//            Response response = resourceWebTarget
//                    .request(MediaType.APPLICATION_XML_TYPE)
//                    .post(Entity.form(form));
//
//            response.bufferEntity();
//
//            System.out.println(response.toString());
//        
//            securityResponse = response.readEntity(SecurityResponse.class);
//
//
//            if(securityResponse.getSuccess()){
//                return Response
//                        .ok()
//                        .build();
//            } else {
//                return Response
//                        .status(response.getStatus())
//                        .entity(securityResponse)
//                        .build();
//            }
//        }
//    }

    @POST
    @Path("activateuser")
    public Response activateUser() throws SOAPException, JAXBException {
         
//         WebTarget resourceWebTarget = webTarget_SOAP.path("registeruser");
         
    	 ClientConfig config = new DefaultClientConfig();
         config.getClasses().add(SoapProvider.class);
         Client c = Client.create(config);
         c.addFilter(new LoggingFilter());
         
         MessageFactory messageFactory = MessageFactory.newInstance();
         SOAPMessage message = messageFactory.createMessage();
         SOAPPart soapPart = message.getSOAPPart();
         SOAPEnvelope envelope = soapPart.getEnvelope();
         SOAPBody body = envelope.getBody();
        
         SOAPElement bodyElement = body.addChildElement(envelope.createName("registerUser", "", securityApiUrl_SOAP));
         
         bodyElement.addChildElement("formParams").addTextNode("59102");
         
         message.saveChanges();
         
         WebResource service = c.resource("http://ws.cdyne.com/WeatherWS/Weather.asmx");
         
         // POST the request
         ClientResponse cr = service.header("SOAPAction", "\"http://ws.cdyne.com/WeatherWS/GetCityWeatherByZIP\"").post(ClientResponse.class, message);
         message = cr.getEntity(SOAPMessage.class);
  
         JAXBContext ctx = JAXBContext.newInstance(SecurityResponse.class);
         Unmarshaller um = ctx.createUnmarshaller();
         SecurityResponse response = (um.unmarshal(message.getSOAPPart().getEnvelope().getBody().extractContentAsDocument(), SecurityResponse.class)).getValue();
         System.out.println("City : " + response.getReason());
         System.out.println("Temperature : " + response.toString());
//         
//         WebResource service = client_SOAP.resource("http://ws.cdyne.com/WeatherWS/Weather.asmx");
//         
//         Response response = resourceWebTarget
// 				                .request(MediaType.APPLICATION_XML_TYPE)
// 				                .post(Entity.form(formParams));
//         
//         response.bufferEntity();
//
//         System.out.println(response.toString());
//         
//         SecurityResponse securityResponse = response.readEntity(SecurityResponse.class);
//
//         System.out.println(securityResponse.toString());
//
//         if(securityResponse.getSuccess()){
             return Response
                     .ok()
                     .build();
//         }else{
//             return Response
//                     .status(response.getStatus())
//                     .entity(securityResponse)
//                     .build();
//         }
    	
       // return "{\"API\": \"activateUser working\"}";
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