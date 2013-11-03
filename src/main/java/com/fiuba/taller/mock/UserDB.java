package com.fiuba.taller.mock;


import java.util.HashMap;
import java.util.Map;

public class UserDB {
    private Map<String, User> users;
    private int userUuid;

    public UserDB(){
        System.out.println("UserDB constructor");
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

    public void createUser(Map<String, String> userData) {
        User newUser = new User(userUuid++, userData);
        users.put(userData.get("username"), newUser);
        System.out.println(users);
    }

}
