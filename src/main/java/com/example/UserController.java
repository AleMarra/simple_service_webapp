package com.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class UserController {

	private Map<String, User> users;
	 
	public UserController(){
	    users = new HashMap<String, User>();
	    users.put("admin", new User(1,"Admin","PAMPITA"));
	    users.put("common", new User(2,"Common","MarioBros"));
	}
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    
    @GET
    @Path("testXML/{userkey}")
    @Produces(MediaType.APPLICATION_XML)
    public User getUserXML(@PathParam("userkey") String userkey) {
       	return users.get(userkey);
    }
    
    @GET
    @Path("testJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserJSON(@QueryParam("userkey")String userkey) {
        
    	return users.get(userkey);
    }
    
    
    @POST
    @Path("testJSON/upload")
    @Produces(MediaType.APPLICATION_JSON)
    public User setUser(User newUser) {
    	newUser.setId(10);
    	
    	return newUser;
    }

    @GET
    @Path("testRedirect")
    @Produces(MediaType.APPLICATION_JSON)
    public String callWs() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://api.despegar.com/cities/tripplanning?includecity=true");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpEntity entity = response.getEntity();
        try {
            return entity != null ? EntityUtils.toString(entity) : null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "laaaaaaallaaa";
    }


}
