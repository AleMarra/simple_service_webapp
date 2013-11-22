package com.fiuba.taller.service.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "register-user-request")
public class RegisterUserRequest {
	
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private Integer padron;
	private String fechaNac;
	private String email;
	private Integer rol;

    public RegisterUserRequest(){}
    
    public RegisterUserRequest(String username,
					    		String password,
					    		String nombre,
					    		String apellido,
					    		Integer padron,
					    		String fechaNac,
					    		String email,
					    		Integer rol)
    {
        super();
        this.setUsername(username);
	    this.setPassword(password);
	    this.setNombre(nombre);
	    this.setApellido(apellido);
	    this.setPadron(padron);
	    this.setFechaNac(fechaNac);
	    this.setEmail(email);
	    this.setRol(rol);
        
        
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "username" + k + username + p;
        result += "password" + k + password + p;
        result += "nombre"   + k + nombre   + p;
        result += "apellido" + k + apellido + p;
        result += "padron"   + k + padron   + p;
        result += "fechaNac" + k + fechaNac + p;
        result += "email"    + k + email    + p;
        result += "rol"      + k + rol;

        return result;
    }
    
    @Override
    public String toString(){
        return toReadable("=", "&");
    }
    
    public String toJSON(){
        return "{" + toReadable(": ", ", ") + "}";
    }

	@XmlElement(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@XmlElement(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement(name = "padron")
	public Integer getPadron() {
		return padron;
	}

	public void setPadron(Integer padron) {
		this.padron = padron;
	}

	@XmlElement(name = "fechaNac")
	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	@XmlElement(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement(name = "rol")
	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	@XmlElement(name = "apellido")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}
