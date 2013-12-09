package com.fiuba.taller.communication.forum.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class ForumDeleteRequest {

	private String username;
	private Integer id_seccion;
	private Integer id_ambito;
	private Integer id_foro;
	
    public ForumDeleteRequest(){}
	
    public ForumDeleteRequest(String username, String section_name, Integer id_ambito, Integer id_foro)
    {
        super();
        this.setUsername(username);
        this.setId_seccion(id_seccion);
        this.setId_ambito(id_ambito);
        this.setId_foro(id_foro);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "username" + k + username + p;
        result += "id_seccion" + k + id_seccion + p;
        result += "id_ambito" + k + id_ambito + p;
        result += "id_foro" + k + id_foro + p;

        return result;
    }

    @Override
    public String toString(){
        return toReadable("=", "&");
    }

    public String toJSON(){
        return "{" + toReadable(": ", ", ") + "}";
    }

    public Form toForm(){
        Form dataAsForm = new Form();

        dataAsForm.param("username", username);
        dataAsForm.param("id_seccion", Integer.toString(id_seccion));
        dataAsForm.param("id_ambito", Integer.toString(id_ambito));
        dataAsForm.param("id_foro", Integer.toString(id_foro));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("username", username);
        dataAsMap.put("id_seccion", Integer.toString(id_seccion));
        dataAsMap.put("id_ambito", Integer.toString(id_ambito));
        dataAsMap.put("id_foro", Integer.toString(id_foro));

        return dataAsMap;
    }
    
	/**
	 * @return the username
	 */
    @XmlElement(name = "username")
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the id_ambito
	 */
	@XmlElement(name = "id_ambito")
	public Integer getId_ambito() {
		return id_ambito;
	}

	/**
	 * @param id_ambito the id_ambito to set
	 */
	public void setId_ambito(Integer id_ambito) {
		this.id_ambito = id_ambito;
	}

	/**
	 * @return the id_foro
	 */
	@XmlElement(name = "id_foro")
	public Integer getId_foro() {
		return id_foro;
	}

	/**
	 * @param id_foro the id_foro to set
	 */
	public void setId_foro(Integer id_foro) {
		this.id_foro = id_foro;
	}

	/**
	 * @return the id_seccion
	 */
	@XmlElement(name = "id_seccion")
	public Integer getId_seccion() {
		return id_seccion;
	}

	/**
	 * @param id_seccion the id_seccion to set
	 */
	public void setId_seccion(Integer id_seccion) {
		this.id_seccion = id_seccion;
	}
    
	
}
