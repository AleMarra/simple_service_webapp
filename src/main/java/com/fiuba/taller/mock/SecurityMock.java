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
            String reason = user == null ? "Usuario no encontrado" : "Contraseña inválida";
        	securityEntity = new SecurityResponse(false, reason);
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
        
        SecurityResponse securityEntity = null;
        
        if (username != null) {
            authenticatedUsers.remove(authToken);
            securityEntity = new SecurityResponse(true, "Logout exitoso");
        } else {
        	
        	securityEntity = new SecurityResponse(true, "Token inválida");

        }
        return Response
        		.status(200)
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

        return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();
    }

    @POST
    @Path("activateuser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response activateUser(@FormParam("username") String username) {
    	
    	boolean status = false;
    	String reason = "";
    	
    	if(username == null){
    		status = false;
    		reason = "Parametros Invalidos: username vacio";
    	}else{
    		User user = users.getUserByUsername(username);
    		if(user == null){
    			status = false;
        		reason = "Usuario inexistente";
    		}else{
    			user.activate();
    			if(user.isActive()){
    				status = true;
            		reason = "Usuario Activado";
    			}else{
    				status = false;
            		reason = "Usuario No Activado";
    			}
    		}
    	}
    	
    	SecurityResponse securityEntity = new SecurityResponse(status, reason);
    	
    	return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();	
    }

    @POST
    @Path("changepassword")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response changePassword(@FormParam("oldPassword") String oldPassword, @FormParam("newPassword") String newPassword, @FormParam("authToken") String authToken) {
    	boolean status = false;
    	String reason = "";
    	
    	String username = authenticatedUsers.get(authToken);
    	User user = users.getUserByUsername(username);
    	if(user != null){
    		if(user.getPassword().equals(oldPassword)){
    			user.changePassword(newPassword);
    			if(user.getPassword().equals(newPassword)){
    				status = true;
        			reason = "Contraseña Actualizada Correctamente";
    			}else{
    				status = true;
        			reason = "Contraseña No Actualizada Correctamente";
    			}
    		}else{
    			status = false;
    			reason = "Contraseña Incorrecta";
    		}
    	}else{
    		status = false;
			reason = "Usuario Inexistente";
    	}
    	
    	SecurityResponse securityEntity = new SecurityResponse(status, reason);
    	
    	return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();
    }
    
    @POST
    @Path("resetpassword")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response changePassword(@FormParam("userId") String userId, @FormParam("authToken") String authToken) {
    	boolean status = false;
    	String reason = "";
    	
    	String username = authenticatedUsers.get(authToken);
    	User loggedUser = users.getUserByUsername(username);
    	User user; 
    	
    	if(userId != null){
    		int id = Integer.parseInt(userId);
    		user = users.getUserById(id);
		}else{
			user = loggedUser;
		}
    	
    	if(loggedUser != null){
    		if(loggedUser.canResetPassword()){
    			if(user != null){
    				user.resetPassword();
            		if(user.getPassword().equals("123456")){
            			status = true;
                		reason = "Contraseña Reseteada Correctamente";
            		}else{
            			status = false;
                		reason = "Contraseña No Reseteada Correctamente";
            		}
    			}else{
    				status = false;
            		reason = "Usuario no encontrado";
    			}
    		}else{
    			status = false;
    			reason = "No tienes permisos para resetear contraseñas.";
    		}
    	}else{
    		status = false;
			reason = "Usuario Inexistente";
    	}
    	
    	SecurityResponse securityEntity = new SecurityResponse(status, reason);
    	
    	return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();
    }
    
    @POST
    @Path("disableaccount")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response disableAccount(@FormParam("userId") String userId, @FormParam("authToken") String authToken) {
    	boolean status = false;
    	String reason = "";
    	
    	String username = authenticatedUsers.get(authToken);
    	User loggedUser = users.getUserByUsername(username);
    	int id = Integer.parseInt(userId);
		User user = users.getUserById(id);
		
		if(loggedUser != null){
			if(loggedUser.canDisableAccount()){
				if(user != null){
					user.disableAccount();
					if(user.isDisable()){
						status = true;
	            		reason = "Usuario deshabilitado correctamente";
					}else{
						status = false;
	            		reason = "Usuario no deshabilitado correctamente";
					}
				}else{
					status = false;
            		reason = "Usuario no encontrado";
				}
			}
		}else{
			status = false;
			reason = "Usuario Inexistente";
		}
		
		SecurityResponse securityEntity = new SecurityResponse(status, reason);
    	
    	return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();
    }

    @POST
    @Path("enableaccount")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public Response enableAccount(@FormParam("userId") String userId, @FormParam("authToken") String authToken) {
    	boolean status = false;
    	String reason = "";
    	
    	String username = authenticatedUsers.get(authToken);
    	User loggedUser = users.getUserByUsername(username);
    	int id = Integer.parseInt(userId);
		User user = users.getUserById(id);
		
		if(loggedUser != null){
			if(loggedUser.canEnableAccount()){
				if(user != null){
					user.enableAccount();
					if(user.isEnable()){
						status = true;
	            		reason = "Usuario habilitado correctamente";
					}else{
						status = false;
	            		reason = "Usuario no habilitado correctamente";
					}
				}else{
					status = false;
            		reason = "Usuario no encontrado";
				}
			}
		}else{
			status = false;
			reason = "Usuario Inexistente";
		}
		
		SecurityResponse securityEntity = new SecurityResponse(status, reason);
    	
    	return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();
    }


    @POST
    @Path("enableaccountfromemaill")
    public Response enableAccountFromEmaill(@FormParam("enabledToken") String authToken) {
    	
    	boolean status = false;
    	String reason = "";
    	
    	String username = authenticatedUsers.get(authToken);
    	User loggedUser = users.getUserByUsername(username);
		
    	if(loggedUser != null){
    		if(loggedUser.canEnableAccount()){
    			loggedUser.enableAccount();
    			if(loggedUser.isEnable()){
    				status = true;
    				reason = "Usuario habilitado correctamente";
    			}else{
    				status = false;
    				reason = "Usuario no habilitado correctamente";
    			}
    		}
    	}else{
    		status = false;
    		reason = "Usuario Inexistente";
    	}
		
		SecurityResponse securityEntity = new SecurityResponse(status, reason);
    	
    	return Response
        		.status(200)
        		.entity(securityEntity)
        		.build();
    }

}
