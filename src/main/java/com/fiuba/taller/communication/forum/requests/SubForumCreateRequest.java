package com.fiuba.taller.communication.forum.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class SubForumCreateRequest {

	private String username;
	private String subforum_name;
	private Integer id_contenedor;
	private Integer id_ambito;
	private Integer id_foro;
	
    public SubForumCreateRequest(){}
	
    public SubForumCreateRequest(String username, String section_name, Integer id_ambito, Integer id_foro)
    {
        super();
        this.setUsername(username);
        this.setSubforum_name(subforum_name);
        this.setId_ambito(id_ambito);
        this.setId_foro(id_foro);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "username" + k + username + p;
        result += "subforum_name" + k + subforum_name + p;
        result += "id_contenedor" + k + id_contenedor + p;
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
        dataAsForm.param("subforum_name", subforum_name);
        dataAsForm.param("id_contenedor", Integer.toString(id_contenedor));
        dataAsForm.param("id_ambito", Integer.toString(id_ambito));
        dataAsForm.param("id_foro", Integer.toString(id_foro));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("username", username);
        dataAsMap.put("section_name", subforum_name);
        dataAsMap.put("id_contenedor", Integer.toString(id_contenedor));
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
	 * @return the subforum_name
	 */
    @XmlElement(name = "subforum_name")
	public String getSubforum_name() {
		return subforum_name;
	}

	/**
	 * @param subforum_name the subforum_name to set
	 */
	public void setSubforum_name(String subforum_name) {
		this.subforum_name = subforum_name;
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
    
}
