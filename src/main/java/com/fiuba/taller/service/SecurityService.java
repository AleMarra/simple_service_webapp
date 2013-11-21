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
import com.cdyne.ws.weatherws.GetCityWeatherByZIPResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
 
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
//import javax.ws.rs.client.Client;
import com.fiuba.taller.service.requests.LoginRequest;
import com.fiuba.taller.service.requests.EnableAccountRequest;
import com.fiuba.taller.service.requests.RegisterUserRequest;

import javax.ws.rs.*;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Path("/securityservice")
@Produces(MediaType.APPLICATION_JSON)
public class SecurityService {

    static private String securityApiUrl_SOAP = "http://localhost:8080/taller-webapp/services/SecurityMockSOAP";
	static private String securityApiUrl = "http://localhost:8080/taller-webapp/api/mocks";
	
    @POST
    @Path("registeruser")
    public Response registerUser() throws SOAPException, JAXBException {
         
         
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
         
         WebResource service = c.resource(securityApiUrl_SOAP);
         
         // POST the request
         ClientResponse cr = service.header("SOAPAction", "\"http://localhost:8080/taller-webapp/services/SecurityMockSOAP/registeruser\"").post(ClientResponse.class, message);
         message = cr.getEntity(SOAPMessage.class);
  
         JAXBContext ctx = JAXBContext.newInstance(SecurityResponse.class);
         Unmarshaller um = ctx.createUnmarshaller();
         SecurityResponse response = (um.unmarshal(message.getSOAPPart().getEnvelope().getBody().extractContentAsDocument(), SecurityResponse.class)).getValue();
         System.out.println("City : " + response.getReason());
         System.out.println("Temperature : " + response.toString());

         return Response
                     .ok()
                     .build();

    }


//
//	@POST
//	@Path("registeruser")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response registerUser(RegisterUserRequest request) {
//		System.out.println(request);
//		WebTarget resourceWebTarget = webTarget.path("registeruser");
//
//		Response response = resourceWebTarget.request(
//				MediaType.APPLICATION_XML_TYPE).post(
//				Entity.form(request.toForm()));
//
//		System.out.println(request.toString());
//
//		response.bufferEntity();
//
//		System.out.println(response.toString());
//
//		SecurityResponse securityResponse = response
//				.readEntity(SecurityResponse.class);
//
//		System.out.println(securityResponse.toString());
//
//		if (securityResponse.getSuccess()) {
//			return Response.ok().entity(securityResponse).build();
//		} else {
//			return Response.status(response.getStatus())
//					.entity(securityResponse).build();
//		}
//	}
//
//	@POST
//	@Path("login")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response login(LoginRequest request) {
//
//		WebTarget resourceWebTarget = webTarget.path("login");
//		Response response = resourceWebTarget.request(
//				MediaType.APPLICATION_XML_TYPE).post(
//				Entity.form(request.toForm()));
//
//		System.out.println(request.toString());
//
//		response.bufferEntity();
//
//		System.out.println(response.toString());
//		System.out.println(response.readEntity(String.class));
//
//		SecurityResponse securityResponse = response
//				.readEntity(SecurityResponse.class);
//
//		if (securityResponse.getSuccess()) {
//			return Response
//					.ok()
//					.cookie(new NewCookie("authToken", securityResponse
//							.getAuthToken())).build();
//		} else {
//			return Response.status(response.getStatus())
//					.entity(securityResponse).build();
//		}
//	}
//
//	@POST
//	@Path("logout")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response logout(@CookieParam("authToken") String authToken) {
//		System.out.println("@CookieParam: " + authToken);
//		Form form = new Form();
//		form.param("authToken", authToken);
//
//		WebTarget resourceWebTarget = webTarget.path("logout");
//		Response response = resourceWebTarget.request(
//				MediaType.APPLICATION_XML_TYPE).post(Entity.form(form));
//
//		response.bufferEntity();
//
//		System.out.println(response.toString());
//
//		SecurityResponse securityResponse = response
//				.readEntity(SecurityResponse.class);
//
//		if (securityResponse.getSuccess()) {
//			return Response
//					.ok()
//					.header("Set-Cookie",
//							"authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
//					.build();
//		} else {
//			return Response.status(response.getStatus())
//					.entity(securityResponse).build();
//		}
//	}
//
//	@POST
//	@Path("isloggedin")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response isLoggedIn(@CookieParam("authToken") String authToken) {
//		SecurityResponse securityResponse;
//
//		if (authToken == null) {
//			securityResponse = new SecurityResponse(false, "No token");
//			return Response.ok().entity(securityResponse).build();
//		} else {
//			System.out.println("@CookieParam: " + authToken);
//			Form form = new Form();
//			form.param("authToken", authToken);
//
//			WebTarget resourceWebTarget = webTarget.path("isvalidtoken");
//			Response response = resourceWebTarget.request(
//					MediaType.APPLICATION_XML_TYPE).post(Entity.form(form));
//
//			response.bufferEntity();
//
//			System.out.println(response.toString());
//
//			securityResponse = response.readEntity(SecurityResponse.class);
//
//			if (securityResponse.getSuccess()) {
//				return Response.ok().build();
//			} else {
//				return Response.status(response.getStatus())
//						.entity(securityResponse).build();
//			}
//		}
//	}
//
//	@POST
//	@Path("activateuser")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response activateUser(LoginRequest request) {
//		SecurityResponse securityResponse;
//
//		if (request.getUsername() == "") {
//			securityResponse = new SecurityResponse(false,
//					"Parametros Invalidos: username vacio");
//			return Response.ok().entity(securityResponse).build();
//		} else {
//			WebTarget resourceWebTarget = webTarget.path("activateuser");
//			Response response = resourceWebTarget.request(
//					MediaType.APPLICATION_XML_TYPE).post(
//					Entity.form(request.toForm()));
//
//			response.bufferEntity();
//
//			System.out.println(response.toString());
//			System.out.println(response.readEntity(String.class));
//
//			securityResponse = response.readEntity(SecurityResponse.class);
//
//			if (securityResponse.getSuccess()) {
//				return Response.ok().entity(securityResponse).build();
//			} else {
//				return Response.status(response.getStatus())
//						.entity(securityResponse).build();
//			}
//		}
//	}
//
//	@POST
//	@Path("changepassword")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response changePassword(EnableAccountRequest request,
//			@CookieParam("authToken") String authToken) {
//		SecurityResponse securityResponse;
//
//		if (request.getOldPassword() == "" || request.getOldPassword() == "") {
//			securityResponse = new SecurityResponse(false,
//					"Parametros Invalidos");
//			return Response.ok().entity(securityResponse).build();
//		} else {
//			System.out.println("@CookieParam: " + authToken);
//
//			Form form = request.toForm();
//			form.param("authToken", authToken);
//
//			WebTarget resourceWebTarget = webTarget.path("changepassword");
//			Response response = resourceWebTarget.request(
//					MediaType.APPLICATION_XML_TYPE).post(Entity.form(form));
//
//			response.bufferEntity();
//
//			System.out.println(response.toString());
//
//			securityResponse = response.readEntity(SecurityResponse.class);
//
//			if (securityResponse.getSuccess()) {
//				return Response.ok().entity(securityResponse).build();
//			} else {
//				return Response.status(response.getStatus())
//						.entity(securityResponse).build();
//			}
//		}
//	}
//
//	@POST
//	@Path("resetpassword")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response resetPassword(LoginRequest request,
//			@CookieParam("authToken") String authToken) {
//		SecurityResponse securityResponse;
//
//		System.out.println("@CookieParam: " + authToken);
//
//		if (request.getUsername() == "") {
//			securityResponse = new SecurityResponse(false,
//					"Parametros Invalidos: username vacio");
//			return Response.ok().entity(securityResponse).build();
//		} else {
//
//			Form form = request.toForm();
//			form.param("authToken", authToken);
//
//			WebTarget resourceWebTarget = webTarget.path("resetpassword");
//			Response response = resourceWebTarget.request(
//					MediaType.APPLICATION_XML_TYPE).post(Entity.form(form));
//
//			response.bufferEntity();
//
//			System.out.println(response.toString());
//
//			securityResponse = response.readEntity(SecurityResponse.class);
//
//			if (securityResponse.getSuccess()) {
//				return Response.ok().entity(securityResponse).build();
//			} else {
//				return Response.status(response.getStatus())
//						.entity(securityResponse).build();
//			}
//		}
//	}
//
//	@POST
//	@Path("disableaccount")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response disableAccount(LoginRequest request,
//			@CookieParam("authToken") String authToken) 
//	{
//		SecurityResponse securityResponse;
//
//		if (request.getUsername() == "") {
//			securityResponse = new SecurityResponse(false,
//					"Parametros Invalidos: username vacio");
//			return Response.ok().entity(securityResponse).build();
//		} else {
//			System.out.println("@CookieParam: " + authToken);
//
//			Form form = request.toForm();
//			form.param("authToken", authToken);
//
//			WebTarget resourceWebTarget = webTarget.path("disableaccount");
//			Response response = resourceWebTarget.request(
//					MediaType.APPLICATION_XML_TYPE).post(Entity.form(form));
//
//			response.bufferEntity();
//
//			System.out.println(response.toString());
//
//			securityResponse = response.readEntity(SecurityResponse.class);
//
//			if (securityResponse.getSuccess()) {
//				return Response.ok().entity(securityResponse).build();
//			} else {
//				return Response.status(response.getStatus())
//						.entity(securityResponse).build();
//			}
//		}
//	}
//
//	@POST
//	@Path("enableaccount")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	public Response enableAccount(EnableAccountRequest request,
//			@CookieParam("authToken") String authToken) 
//	{
//		SecurityResponse securityResponse;
//
//		if (request.getUsername() == "") {
//			securityResponse = new SecurityResponse(false,
//					"Parametros Invalidos: username vacio");
//			return Response.ok().entity(securityResponse).build();
//		} else {
//
//			System.out.println("@CookieParam: " + authToken);
//
//			Form form = request.toForm();
//			form.param("authToken", authToken);
//
//			WebTarget resourceWebTarget = webTarget.path("enableaccount");
//			Response response = resourceWebTarget.request(
//					MediaType.APPLICATION_XML_TYPE).post(Entity.form(form));
//
//			response.bufferEntity();
//
//			System.out.println(response.toString());
//
//			securityResponse = response.readEntity(SecurityResponse.class);
//
//			if (securityResponse.getSuccess()) {
//				return Response.ok().entity(securityResponse).build();
//			} else {
//				return Response.status(response.getStatus())
//						.entity(securityResponse).build();
//			}
//		}
//	}
//
//	@POST
//	@Path("enableaccountfromemaill")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response enableAccountFromEmaill(EnableAccountRequest request) {
//		
//		SecurityResponse securityResponse;
//
//		if (request.getEnabledToken() == "") {
//			securityResponse = new SecurityResponse(false,
//					"Parametros Invalidos: token vacio");
//			return Response.ok().entity(securityResponse).build();
//		} else {
//
//			Form form = request.toForm();
//
//			WebTarget resourceWebTarget = webTarget.path("enableaccountfromemail");
//			Response response = resourceWebTarget.request(
//					MediaType.APPLICATION_XML_TYPE).post(Entity.form(form));
//
//			response.bufferEntity();
//
//			System.out.println(response.toString());
//
//			securityResponse = response.readEntity(SecurityResponse.class);
//
//			if (securityResponse.getSuccess()) {
//				return Response.ok().entity(securityResponse).build();
//			} else {
//				return Response.status(response.getStatus())
//						.entity(securityResponse).build();
//			}
//		}
//	}
}