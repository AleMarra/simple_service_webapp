package com.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	    users.put("common1", new User(2,"Common","MarioBros"));
	    users.put("common2", new User(3,"Common","Juan Carlos Pelotudo"));
	    users.put("common3", new User(4,"Common","Luis Almirante Brown"));
	    users.put("common4", new User(5,"Common","Jesús de Laferrere"));
	    users.put("common5", new User(6,"Common","Ministro de Ahorro Postal"));
	    users.put("common6", new User(7,"Common","Mimo Páez"));
	    users.put("common7", new User(8,"Common","Quiste Sebáceo"));
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

        return "laaaallaaa";
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list(@QueryParam("max") Integer max) {
        Integer qty = max != null? max : 5;
        List< User > list = new ArrayList< User >(users.values());
        return list.subList(0, qty);
    }

}
