package com.fiuba.taller.mock;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Path("/")
@Produces(MediaType.APPLICATION_XML)
public class SecurityMock {

    static private String [] authTokens = new String[]{
            "bad18eba1ff45jk7858b8ae88a77fa30",
            "bad18eba1ff45jk7858b8ae88a77fa31",
            "bad18eba1ff45jk7858b8ae88a77fa32"
    };

    @POST
    @Path("registeruser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String registerUser(MultivaluedMap<String, String> formParams) {
        System.out.println(formParams);
        return "[Mock] registerUser working";
    }

    @POST
    @Path("login")
    public String login() {
        return "[Mock] login working";
    }

    @POST
    @Path("logout")
    public String logout() {
        return "[Mock] logout working";
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
