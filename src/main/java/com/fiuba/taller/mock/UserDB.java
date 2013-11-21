package com.fiuba.taller.mock;


import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;

public class UserDB {
    private Map<String, User> users;
    private int userUuid;

    public UserDB(){
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
        hardcodedUserData.put("rol", "1");
        hardcodedUserData.put("active", "0");
        User hardcodedUser = new User(userUuid++, hardcodedUserData);
        users = new HashMap<String, User>();
        users.put(hardcodedUserUsername, hardcodedUser);
    }

    public boolean createUser(Map<String, String> userData) {
        boolean success = userData.containsKey("username") && !users.containsKey(userData.get("username"));
        if (success) {
            User newUser = new User(userUuid++, userData);
            users.put(userData.get("username"), newUser);
        }

        return success;
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }
    
    public User getUserById(int id) {
    	Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
        	Map.Entry pair = (Map.Entry) it.next();
            String username = (String) pair.getKey();
            User user = (User) pair.getValue();
            if(user.isUser(id)){
            	return user;
            }
        }
        return null;
    }
}
