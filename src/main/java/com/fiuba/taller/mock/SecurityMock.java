package com.fiuba.taller.mock;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_XML)
public class SecurityMock {

    private Map<String, User> users;
    private int userUuid;
    static private String [] authTokens = new String[]{
            "bad18eba1ff45jk7858b8ae88a77fa30",
            "bad18eba1ff45jk7858b8ae88a77fa31",
            "bad18eba1ff45jk7858b8ae88a77fa32"
    };

    public SecurityMock(){
        userUuid = 0;
        Map<String, String> hardcodedUserData = new HashMap<String, String>();
        String hardcodedUserUsername = "juan";
        hardcodedUserData.put("username", hardcodedUserUsername);
        hardcodedUserData.put("password", "qwerty");
        hardcodedUserData.put("nombre", "Juan Carlos");
        hardcodedUserData.put("apellido", "García");
        hardcodedUserData.put("padron", "90000");
        hardcodedUserData.put("fechaNac", "1989-11-30");
        hardcodedUserData.put("email", "juan@gmail.com");
        hardcodedUserData.put("rol", "2");
        User hardcodedUser = new User(userUuid++, hardcodedUserData);
        users = new HashMap<String, User>();
        users.put(hardcodedUserUsername, hardcodedUser);
    }

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
        User newUser = new User(userUuid++, userData);
        users.put(userData.get("username"), newUser);
        System.out.println(users);
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
