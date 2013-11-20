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
    private int active;
    private int habilitado;

	public User() {}

    public User(int id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = 0;
        this.habilitado = 0;
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
        this.active = 0;
        this.habilitado = 0;
    }

	@XmlElement(name = "id")
	public int getId() {
		return id;
	}
	
    @XmlElement(name = "username")
    public String getUsername() {
        return username;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    @XmlElement(name = "active")
    public boolean isActive(){
    	return (this.active == 1);
    }
    
    public void activate(){
    	this.active = 1;
    }
    
    public void changePassword(String password){
    	this.password = password;
    }
    
    @XmlElement(name = "canReset")
    public boolean canResetPassword(){
    	//SUPONGO Q EL ROL 1 ES DE LOS ADMIN QUE SON DIOS Y PUEDEN HACER LO Q QUIERAN
    	return (this.rol == 1);
    }
    
    @XmlElement(name = "canChange")
    public boolean canChangePassword(){
    	//SUPONGO Q EL ROL 1 ES DE LOS ADMIN QUE SON DIOS Y PUEDEN HACER LO Q QUIERAN
    	return (this.rol == 1);
    }
    
    @XmlElement(name = "canDisable")
    public boolean canDisableAccount(){
    	//SUPONGO Q EL ROL 1 ES DE LOS ADMIN QUE SON DIOS Y PUEDEN HACER LO Q QUIERAN
    	return (this.rol == 1);
    }
    
    public void disableAccount(){
    	this.habilitado = 0;
    }
    
    public boolean isDisable(){
    	return (this.habilitado == 0);
    }
    
    @XmlElement(name = "canEnable")
    public boolean canEnableAccount(){
    	//SUPONGO Q EL ROL 1 ES DE LOS ADMIN QUE SON DIOS Y PUEDEN HACER LO Q QUIERAN
    	return (this.rol == 1);
    }
    
    public void enableAccount(){
    	this.habilitado = 1;
    }
    
    public boolean isEnable(){
    	return (this.habilitado == 1);
    }
    
    public void resetPassword(){
    	//AGREGAR ALGUN METODO QUE GENERE UNA PASS RANDOM
    	this.changePassword("123456");
    	
    	//AGREGAR FUNCIONALIDAD QUE ENVIE EL MAIL
    }
    
    public boolean isUser(int userId){
    	return (this.id == userId);
    }
	

}