package com.fiuba.taller.mock;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_XML)
public class SecurityMock {

    static private UserDB users = new UserDB();
    static private String baseAuthToken = "bad18eba1ff45jk7858b8ae88a77fa30";
    static private Map<String, String> authenticatedUsers = new HashMap<String, String>();

    @POST
    @Path("registeruser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String registerUser(MultivaluedMap<String, String> formParams) {
        Map<String, String> userData = new HashMap<String, String>();
        Iterator it = formParams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String key = (String) pair.getKey();
            List<String> value = (List<String>) pair.getValue();
            userData.put(key, value.get(0));
        }

        String xmlResponse;
        if (users.createUser(userData)) {
            xmlResponse =
                "<securityResponse>\n"       +
                "    <success>1</success>\n" +
                "</securityResponse>";
        } else {
            xmlResponse =
                "<response>\n"                                +
                "    <success>0</success>\n"                  +
                "    <reason>El usuario ya existe</reason>\n" +
                "</response>";

        }
        return xmlResponse;
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String login(@FormParam("username") String username, @FormParam("password") String password) {
        User user = users.getUserByUsername(username);
        String xmlResponse;

        if (user != null && user.getPassword().equals(password)) {
            String authToken = baseAuthToken + Integer.toString(user.getId());
            authenticatedUsers.put(authToken, username);
            xmlResponse =
                "<response>\n"                                   +
                "    <success>1</success>\n"                     +
                "    <authToken>" + authToken + "</authToken>\n" +
                "</response>";
        } else {
            xmlResponse =
                "<response>\n"                               +
                "    <success>0</success>\n"                 +
                "    <reason>Contraseña inválida</reason>\n" +
                "</response>";

        }
        return xmlResponse;
    }

    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String logout(@FormParam("authToken") String authToken) {
        String username = authenticatedUsers.get(authToken);
        String xmlResponse;

        if (username != null) {
            authenticatedUsers.remove(authToken);
            xmlResponse =
                    "<response>\n"                                   +
                    "    <success>1</success>\n"                     +
                    "</response>";
        } else {
            xmlResponse =
                    "<response>\n"                          +
                    "    <success>0</success>\n"            +
                    "    <reason>Token inválida</reason>\n" +
                    "</response>";

        }
        return xmlResponse;
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
