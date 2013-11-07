package com.example;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/comunicaciones")
public class ComController {
	
	@POST
    @Path("crearSeccion")
    @Produces(MediaType.APPLICATION_JSON)
    public Section setUser(Section newSection) {    
		// Validar permisos del rol
    	return newSection;
    }
	
}
