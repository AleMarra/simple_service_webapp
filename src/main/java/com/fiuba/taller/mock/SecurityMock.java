package com.fiuba.taller.mock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.fiuba.taller.service.SecurityResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Path("/")
public class SecurityMock {

    static private UserDB users = new UserDB();
    static private String baseAuthToken = "bad18eba1ff45jk7858b8ae88a77fa30";
    static private Map<String, String> authenticatedUsers = new HashMap<String, String>();

    @POST
    @Path("registeruser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response registerUser(MultivaluedMap<String, String> formParams) {

    	Map<String, String> userData = new HashMap<String, String>();
        Iterator it = formParams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String key = (String) pair.getKey();
            List<String> value = (List<String>) pair.getValue();
            userData.put(key, value.get(0));
        }
        
        SecurityResponse securityEntity = null;
        if (users.createUser(userData)) {
        	securityEntity =  new SecurityResponse(true, "Usuario creado exitosamente");
        } else {
        	securityEntity = new SecurityResponse(false, "El usuario ya existe");
        }

        return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        User user = users.getUserByUsername(username);

        SecurityResponse securityEntity = null;

        if (user != null && user.getPassword().equals(password)) {
            String authToken = baseAuthToken + Integer.toString(user.getId());
            authenticatedUsers.put(authToken, username);
            
            securityEntity = new SecurityResponse(true, "Login exitoso", authToken);
            
        } else {
        	securityEntity = new SecurityResponse(false, "Contraseña inválida");
        }

        return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();
    }

    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response logout(@FormParam("authToken") String authToken) {
        String username = authenticatedUsers.get(authToken);
        //String xmlResponse;
        
        SecurityResponse securityEntity = null;
        
        if (username != null) {
            authenticatedUsers.remove(authToken);
            
            securityEntity = new SecurityResponse(true, "Logout exitoso");
//            xmlResponse =
//                    "<response>\n"                                   +
//                    "    <success>1</success>\n"                     +
//                    "</response>";
        } else {
        	securityEntity = new SecurityResponse(true, "Token inválida");
//            xmlResponse =
//                    "<response>\n"                          +
//                    "    <success>0</success>\n"            +
//                    "    <reason>Token inválida</reason>\n" +
//                    "</response>";

        }
        //return xmlResponse;
        return Response.status(200)
        		.entity(securityEntity)
        		.build();
    }
    
    @POST
    @Path("isvalidtoken")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response isValidToken(@FormParam("authToken") String authToken) {
        String username = authenticatedUsers.get(authToken);
        //String xmlResponse;
        
        SecurityResponse securityEntity = null;
        
        if (username != null) {
            //authenticatedUsers.refreshTime(authToken);
            securityEntity = new SecurityResponse(true, "logged in");
        } else {
        	securityEntity = new SecurityResponse(false, "Token invalida");
        }

        return Response.status(200)
        		.entity(securityEntity)
        		.build();
    }

    @POST
    @Path("activateuser")
    public String activateUser() {
        return "[Mock] activateUser working";
    }

    @POST
    @Path("changepassword")
    public String changePassword() {
        return "[Mock] changePassword working";
    }

    @POST
    @Path("resetpassword")
    public String resetPassword() {
        return "[Mock] resetPassword working";
    }

    @POST
    @Path("disableaccount")
    public String disableAccount() {
        return "[Mock] disableAccount working";
    }

    @POST
    @Path("enableaccount")
    public String enableAccount() {
        return "[Mock] enableAccount working";
    }

    @POST
    @Path("enableaccountfromemaill")
    public String enableAccountFromEmaill() {
        return "[Mock] enableAccountFromEmaill working";
    }

}
