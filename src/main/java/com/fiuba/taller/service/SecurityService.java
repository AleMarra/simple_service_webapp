package com.fiuba.taller.service;

import java.io.IOException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import com.fiuba.taller.BaseService;
import com.fiuba.taller.service.requests.EnableAccountRequest;
import com.fiuba.taller.service.requests.LoginRequest;
import com.fiuba.taller.service.requests.ChangePasswordRequest;
import com.fiuba.taller.service.requests.RegisterUserRequest;
import javax.ws.rs.*;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import wtp.loginapihelper.wtp.LoginAPIHelperStub;


@Path("/securityservice")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class SecurityService  extends BaseService {

	@POST
	@Path("registeruser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(RegisterUserRequest request)
			throws ParserConfigurationException, SAXException, IOException
	{
		
		System.out.println(request);
		
		// Init
		SecurityResponse response = new SecurityResponse();

		LoginAPIHelperStub api = new LoginAPIHelperStub();
		LoginAPIHelperStub.RegisterUser securityRequest = new LoginAPIHelperStub.RegisterUser();
		LoginAPIHelperStub.RegisterUserResponse wsResponse = new LoginAPIHelperStub.RegisterUserResponse();

		// Armar el request
		securityRequest.setUsername(request.getUsername());
		securityRequest.setPassword(request.getPassword());
		securityRequest.setNombres(request.getNombre());
		securityRequest.setApellido(request.getApellido());
		securityRequest.setPadron(Integer.toString(request.getPadron()));
		securityRequest.setFecha(request.getFechaNac());
		securityRequest.setEmail(request.getEmail());
		securityRequest.setRol(request.getRol());

		// Hacer el request
        try {
		    wsResponse = api.registerUser(securityRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildServiceUnavailable("crear usuario", error.getReason());
        }

        // Parsear el response
		Document doc = getDoc(wsResponse.get_return());
		Node node = getNode(doc, "response");

        String successString = getFirstElementValue( node, "success");
        if (successString == null) {
            return buildWrongXmlError("success");
        }
        boolean success = successString.equals(TRUE_STRING);
        response.setSuccess(success);

		if (success){
			response.setReason("Usuario creado exitosamente");
		}else{
    		response.setReason(getFirstElementValue(node, "reason"));
		}

		return Response.ok().entity(response).build();
	}

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest request)
			throws ParserConfigurationException, SAXException, IOException
	{

		System.out.println(request);
		
		// Init
		SecurityResponse response = new SecurityResponse();
		
		LoginAPIHelperStub api = new LoginAPIHelperStub();  
		LoginAPIHelperStub.Login securityRequest = new LoginAPIHelperStub.Login();
		LoginAPIHelperStub.LoginResponse wsResponse = new LoginAPIHelperStub.LoginResponse();
		
		securityRequest.setUsername(request.getUsername());
		securityRequest.setPassword(request.getPassword());
		
		// Hacer el request
        try {
            wsResponse = api.login(securityRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildServiceUnavailable("login", error.getReason());
        }

		// Parsear el response
		Document doc = getDoc(wsResponse.get_return());
		Node node = getNode(doc, "response");

		String successString = getFirstElementValue( node, "success");
        if (successString == null) {
            return buildWrongXmlError("success");
        }
        boolean success = successString.equals(TRUE_STRING);

		if (success){

			return Response.ok()
					.cookie(new NewCookie("authToken",
							getFirstElementValue(node, "authToken")))
					.build();

		}else{
			response.setSuccess(success);
			response.setReason(getFirstElementValue(node, "reason"));
			
			return Response.ok()
					.entity(response)
					.build();
		}

	}

	@POST
	@Path("logout")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logout(@CookieParam("authToken") String authToken) 
			throws ParserConfigurationException, SAXException, IOException
	{
		System.out.println("@CookieParam: " + authToken);
		// Init
		SecurityResponse response = new SecurityResponse();

		LoginAPIHelperStub api = new LoginAPIHelperStub();  
		LoginAPIHelperStub.Logout securityRequest = new LoginAPIHelperStub.Logout();
		LoginAPIHelperStub.LogoutResponse wsResponse = new LoginAPIHelperStub.LogoutResponse();

		securityRequest.setAuthToken(authToken);

		// Hacer el request
        try {
            wsResponse = api.logout(securityRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildServiceUnavailable("logout", error.getReason());
        }

		// Parsear el response
		Document doc = getDoc(wsResponse.get_return());
		Node node = getNode(doc, "response");

        String successString = getFirstElementValue( node, "success");
        if (successString == null) {
            return buildWrongXmlError("success");
        }
        boolean success = successString.equals(TRUE_STRING);

        if (success){

			return Response.ok()
					.header("Set-Cookie",
							"authToken=deleted;Expires=Thu, 01-Jan-1970 00:00:01 GMT")
					.build();

		}else{
			response.setSuccess(success);
			response.setReason(getFirstElementValue(node, "reason"));
			
			return Response.ok()
					.entity(response)
					.build();
		}
	}


	@POST
	@Path("isloggedin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response isLoggedIn(@CookieParam("authToken") String authToken) 
			throws ParserConfigurationException, SAXException, IOException
	{
		
		System.out.println("@CookieParam: " + authToken);
		// Init
		SecurityResponse response = new SecurityResponse();

		LoginAPIHelperStub api = new LoginAPIHelperStub();  
		LoginAPIHelperStub.IsTokenValid securityRequest = new LoginAPIHelperStub.IsTokenValid();
		LoginAPIHelperStub.IsTokenValidResponse wsResponse = new LoginAPIHelperStub.IsTokenValidResponse();

		securityRequest.setAuthToken(authToken);

		// Hacer el request
        try {
            wsResponse = api.isTokenValid(securityRequest);
        } catch (AxisFault error) {
            System.out.println(error.getReason());
            return buildServiceUnavailable("sesión", error.getReason());
        }

		// Parsear el response
		Document doc = getDoc(wsResponse.get_return());
		Node node = getNode(doc, "response");

        String successString = getFirstElementValue( node, "success");
        if (successString == null) {
            return buildWrongXmlError("success");
        }
        boolean success = successString.equals(TRUE_STRING);

        if (success){

			return Response.ok()
					.build();

		}else{
			response.setSuccess(success);
			response.setReason(getFirstElementValue(node, "reason"));
			
			return Response.ok()
					.entity(response)
					.build();
		}
	}

	@POST
	@Path("activateuser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response activateUser(LoginRequest request) 
			throws ParserConfigurationException, SAXException, IOException
	{
		System.out.println(request);
		SecurityResponse response = new SecurityResponse();
		
		if (request.getUsername().equals("")) {
			response = new SecurityResponse(false, "Parametros Invalidos: username vacio");
			
			return Response.ok().entity(response).build();
		
		} else {
			
			LoginAPIHelperStub api = new LoginAPIHelperStub();  
			LoginAPIHelperStub.ActivateUser securityRequest = new LoginAPIHelperStub.ActivateUser();
			LoginAPIHelperStub.ActivateUserResponse wsResponse = new LoginAPIHelperStub.ActivateUserResponse();
			
			securityRequest.setUsername(request.getUsername());
			
			// Hacer el request
            try {
                wsResponse = api.activateUser(securityRequest);
            } catch (AxisFault error) {
                System.out.println(error.getReason());
                return buildServiceUnavailable("activar usuario", error.getReason());
            }

			// Parsear el response
			Document doc = getDoc(wsResponse.get_return());
			Node node = getNode(doc, "response");

            String successString = getFirstElementValue( node, "success");
            if (successString == null) {
                return buildWrongXmlError("success");
            }
            boolean success = successString.equals(TRUE_STRING);

            response.setSuccess(success);

            if (success){
				response.setReason("Usuario activado exitosamente");
			}else{
				response.setReason(getFirstElementValue(node, "reason"));
			}
			
			return Response.ok()
					.entity(response)
					.build();
		}
	}

	@POST
	@Path("changepassword")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePassword(ChangePasswordRequest request,@CookieParam("authToken") String authToken)
			throws ParserConfigurationException, SAXException, IOException
	{
		System.out.println(request);
		SecurityResponse response = new SecurityResponse();
		

		if (request.getOldPassword().equals("") || request.getNewPassword().equals("")) {
			response = new SecurityResponse(false,
					"Parametros Invalidos");
			
			return Response.ok().entity(response).build();
			
		} else {
			
			LoginAPIHelperStub api = new LoginAPIHelperStub();  
			LoginAPIHelperStub.ChangePassword securityRequest = new LoginAPIHelperStub.ChangePassword();
			LoginAPIHelperStub.ChangePasswordResponse wsResponse = new LoginAPIHelperStub.ChangePasswordResponse();
			
			securityRequest.setNewPassword(request.getNewPassword());
			securityRequest.setOldPassword(request.getOldPassword() );
			securityRequest.setAuthToken(authToken);
			
			// Hacer el request
            try {
                wsResponse = api.changePassword(securityRequest);
            } catch (AxisFault error) {
                System.out.println(error.getReason());
                return buildServiceUnavailable("cambiar contraseña", error.getReason());
            }

			// Parsear el response
			Document doc = getDoc(wsResponse.get_return());
			Node node = getNode(doc, "response");


            String successString = getFirstElementValue( node, "success");
            if (successString == null) {
                return buildWrongXmlError("success");
            }
            boolean success = successString.equals(TRUE_STRING);

            response.setSuccess(success);

            if (success){
				response.setReason("Contaseña actualizada");
				
			}else{
				response.setReason(getFirstElementValue(node, "reason"));
			}
			
			return Response.ok()
					.entity(response)
					.build();
		}
	}

	@POST
	@Path("resetpassword")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response resetPassword(LoginRequest request,@CookieParam("authToken") String authToken)
			throws ParserConfigurationException, SAXException, IOException
	{
		
		System.out.println(request);
		SecurityResponse response = new SecurityResponse();
		
		if (request.getUsername().equals("")) {
			response = new SecurityResponse(false, "Parametros Invalidos: username vacio");
			
			return Response.ok().entity(response).build();
		
		} else {
			
			LoginAPIHelperStub api = new LoginAPIHelperStub();  
			LoginAPIHelperStub.ResetPassword securityRequest = new LoginAPIHelperStub.ResetPassword();
			LoginAPIHelperStub.ResetPasswordResponse wsResponse = new LoginAPIHelperStub.ResetPasswordResponse();
			
			securityRequest.setAuthToken(authToken);
			securityRequest.setUserId(request.getUsername());
			
			// Hacer el request
            try {
                wsResponse = api.resetPassword(securityRequest);
            } catch (AxisFault error) {
                System.out.println(error.getReason());
                return buildServiceUnavailable("resetear contraseña", error.getReason());
            }

			// Parsear el response
			Document doc = getDoc(wsResponse.get_return());
			Node node = getNode(doc, "response");

            String successString = getFirstElementValue( node, "success");
            if (successString == null) {
                return buildWrongXmlError("success");
            }
            boolean success = successString.equals(TRUE_STRING);

            response.setSuccess(success);
            if (success){
				response.setReason("Contraseña Reseteada");
			}else{
				response.setReason(getFirstElementValue(node, "reason"));
			}
			
			return Response.ok()
					.entity(response)
					.build();
		}
	}

	@POST
	@Path("disableaccount")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response disableAccount(LoginRequest request,@CookieParam("authToken") String authToken)
			throws ParserConfigurationException, SAXException, IOException
	{
		System.out.println(request);
		SecurityResponse response = new SecurityResponse();
		
		if (request.getUsername().equals("")) {
			response = new SecurityResponse(false, "Parametros Invalidos: username vacio");
			
			return Response.ok().entity(response).build();
		
		} else {
			
			LoginAPIHelperStub api = new LoginAPIHelperStub();  
			LoginAPIHelperStub.DisableAccount securityRequest = new LoginAPIHelperStub.DisableAccount();
			LoginAPIHelperStub.DisableAccountResponse wsResponse = new LoginAPIHelperStub.DisableAccountResponse();
			
			securityRequest.setAuthToken(authToken);
			securityRequest.setUserId(request.getUsername());
			
			// Hacer el request
            try {
                wsResponse = api.disableAccount(securityRequest);
            } catch (AxisFault error) {
                System.out.println(error.getReason());
                return buildServiceUnavailable("deshabilitar cuenta", error.getReason());
            }

			// Parsear el response
			Document doc = getDoc(wsResponse.get_return());
			Node node = getNode(doc, "response");

            String successString = getFirstElementValue( node, "success");
            if (successString == null) {
                return buildWrongXmlError("success");
            }
            boolean success = successString.equals(TRUE_STRING);

            response.setSuccess(success);
            if (success){
				response.setReason("Cuenta suspendida");
				
			}else{
				response.setReason(getFirstElementValue(node, "reason"));
				
			}
			
			return Response.ok()
					.entity(response)
					.build();
		}
	}

	@POST
	@Path("enableaccount")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response enableAccount(EnableAccountRequest request,@CookieParam("authToken") String authToken)
			throws ParserConfigurationException, SAXException, IOException
	{
		
		System.out.println(request);
		SecurityResponse response = new SecurityResponse();
		
		if (request.getUsername().equals("")) {
			response = new SecurityResponse(false, "Parametros Invalidos: username vacio");
			
			return Response.ok().entity(response).build();
		
		} else {
			
			LoginAPIHelperStub api = new LoginAPIHelperStub();  
			LoginAPIHelperStub.EnableAccount securityRequest = new LoginAPIHelperStub.EnableAccount();
			LoginAPIHelperStub.EnableAccountResponse wsResponse = new LoginAPIHelperStub.EnableAccountResponse();
			
			securityRequest.setAuthToken(authToken);
			securityRequest.setUserId(request.getUsername());
			
			// Hacer el request
            try {
                wsResponse = api.enableAccount(securityRequest);
            } catch (AxisFault error) {
                System.out.println(error.getReason());
                return buildServiceUnavailable("habilitar cuenta", error.getReason());
            }

			// Parsear el response
			Document doc = getDoc(wsResponse.get_return());
			Node node = getNode(doc, "response");

            String successString = getFirstElementValue( node, "success");
            if (successString == null) {
                return buildWrongXmlError("success");
            }
            boolean success = successString.equals(TRUE_STRING);

            response.setSuccess(success);
            if (success){
				response.setReason("Cuenta habilitada");
				
			}else{
				response.setReason(getFirstElementValue(node, "reason"));
			}
			
			return Response.ok()
					.entity(response)
					.build();
		}
	}

	
//	@POST
//	@Path("enableaccountfromemaill")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response enableAccountFromEmaill(EnableAccountRequest request) 
//			throws ParserConfigurationException, SAXException, IOException
//	{
//		
//		System.out.println(request);
//		SecurityResponse response = new SecurityResponse();
//
//		if (request.getEnabledToken().equals("")) {
//			response = new SecurityResponse(false,
//					"Parametros Invalidos: token vacio");
//			return Response.ok().entity(response).build();
//		} else {
//
//			LoginAPIHelperStub api = new LoginAPIHelperStub();  
//			LoginAPIHelperStub.EnableAccountFromEmail securityRequest = new LoginAPIHelperStub.EnableAccountFromEmail();
//			LoginAPIHelperStub.EnableAccountFromEmailResponse wsResponse = new LoginAPIHelperStub.EnableAccountFromEmailResponse();
//			
//			securityRequest.setAuthToken(request.getEnabledToken());
//			securityRequest.setUserId(request.getUsername());
//			
//			// Hacer el request
//			wsResponse = api.enableAccount(securityRequest);
//
//			// Parsear el response
//			Document doc = getDoc(wsResponse.get_return());
//			Node node = getNode(doc, "response");
//
//          String successString = getFirstElementValue( node, "success");
//          if (successString == null) {
//              return buildWrongXmlError("success");
//          }
//          boolean success = successString.equals(TRUE_STRING);
//
//          response.setSuccess(success);
//          if (success){
//          	response.setReason("Cuenta habilitada");
//				
//			}else{
//				response.setReason(getFirstElementValue(node, "reason"));
//				
//			}
//			
//			return Response.ok()
//					.entity(response)
//					.build();
//		}
//	}
}