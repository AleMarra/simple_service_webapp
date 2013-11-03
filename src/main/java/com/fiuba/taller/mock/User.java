package com.fiuba.taller.mock;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Map;

@XmlRootElement(name = "user-details")
public class User {

	private int id;
	private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String padron;
    private Date fechaNac;
    private String email;
    private int rol;

	public User() {}

    public User(int id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public User(int id, Map<String, String> userData) {
        super();
        this.id = id;
        this.username = userData.get("username");
        this.password = userData.get("password");
        this.nombre = userData.get("nombre");
        this.apellido = userData.get("apellido");
        this.padron = userData.get("padron");
//        this.fechaNac = new Date(userData.get("fechaNac"));
        this.email = userData.get("email");
        this.rol = Integer.parseInt(userData.get("rol"));
    }

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}

	@XmlElement(name = "user")
	public String getUsername() {
		return username;
	}
	

}