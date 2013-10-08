package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class UserControllerSeba extends UserController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("lalala")
    public String lalala() {
        return "Got it from inherited class!";
    }
}
