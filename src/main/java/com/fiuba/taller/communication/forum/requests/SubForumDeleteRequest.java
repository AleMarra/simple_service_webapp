package com.fiuba.taller.communication.forum.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class SubForumDeleteRequest {

	private String username;
	private Integer id_contenedor;
	private Integer id_ambito;
	private Integer id_foro;
	private Integer id_subforo;
	
    public SubForumDeleteRequest(){}
	
    public SubForumDeleteRequest(String username, String section_name, Integer id_ambito, Integer id_foro)
    {
        super();
        this.setUsername(username);
        this.setId_ambito(id_ambito);
        this.setId_foro(id_foro);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "username" + k + username + p;
        result += "id_contenedor" + k + id_contenedor + p;
        result += "id_ambito" + k + id_ambito + p;
        result += "id_foro" + k + id_foro + p;
        result += "id_subforo" + k + id_subforo + p;

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
        dataAsForm.param("id_contenedor", Integer.toString(id_contenedor));
        dataAsForm.param("id_ambito", Integer.toString(id_ambito));
        dataAsForm.param("id_foro", Integer.toString(id_foro));
        dataAsForm.param("id_subforo", Integer.toString(id_subforo));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("username", username);
        dataAsMap.put("id_contenedor", Integer.toString(id_contenedor));
        dataAsMap.put("id_ambito", Integer.toString(id_ambito));
        dataAsMap.put("id_foro", Integer.toString(id_foro));
        dataAsMap.put("id_subforo", Integer.toString(id_subforo));

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
	 * @return the id_subforo
	 */
	@XmlElement(name = "id_subforo")
	public Integer getId_subforo() {
		return id_subforo;
	}

	/**
	 * @param id_subforo the id_subforo to set
	 */
	public void setId_subforo(Integer id_subforo) {
		this.id_subforo = id_subforo;
	}

	/**
	 * @return the id_contenedor
	 */
	@XmlElement(name = "id_contenedor")
	public Integer getId_contenedor() {
		return id_contenedor;
	}

	/**
	 * @param id_contenedor the id_contenedor to set
	 */
	public void setId_contenedor(Integer id_contenedor) {
		this.id_contenedor = id_contenedor;
	}


	
}
