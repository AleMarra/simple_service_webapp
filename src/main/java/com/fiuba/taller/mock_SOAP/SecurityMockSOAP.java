package com.fiuba.taller.mock_SOAP;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.fiuba.taller.mock.UserDB;
import com.fiuba.taller.service.SecurityResponse;

public class SecurityMockSOAP {

	 static private UserDB users = new UserDB();
	 static private String baseAuthToken = "bad18eba1ff45jk7858b8ae88a77fa30";
	 static private Map<String, String> authenticatedUsers = new HashMap<String, String>();

	    
	public String ping(String text) {
	    if (text == null) {
	      return "TEST";
	    }
	    return "Service is up and available, message: " + text;
	  }
	
	public SecurityResponse registerUser(MultivaluedMap<String, String> formParams) {
		
		if (formParams != null) {
			try{
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
					securityEntity = new SecurityResponse(true,
							"Usuario creado exitosamente");
				} else {
					securityEntity = new SecurityResponse(false,
							"El usuario ya existe");
				}
	
				//return Response.status(200).entity(securityEntity).build();
	
				return securityEntity;
			
			}catch(Exception e){
				e.printStackTrace();
				return new SecurityResponse(false, "Datos inválidos con excepcion");
			}
		
		} else {
			
			return new SecurityResponse(false, "Datos inválidos");
		}
    }

}
