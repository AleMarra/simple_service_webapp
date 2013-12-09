package com.fiuba.taller.communication.wall.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class EventEdit {

	private String username;
	private String content;
	private Integer id_ambito;
	private Integer id_muro;
	private Integer id_evento;
	
    public EventEdit(){}
	
    public EventEdit(String username,Integer id_ambito)
    {
        super();
        this.setUsername(username);
        this.setId_ambito(id_ambito);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "username" + k + username + p;
        result += "content" + k + content + p;
        result += "id_ambito" + k + id_ambito + p;
        result += "id_muro" + k + id_muro + p;
        result += "id_evento" + k + id_evento + p;

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
        dataAsForm.param("content", content);
        dataAsForm.param("id_ambito", Integer.toString(id_ambito));
        dataAsForm.param("id_muro", Integer.toString(id_muro));
        dataAsForm.param("id_evento", Integer.toString(id_evento));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("username", username);
        dataAsMap.put("content", content);
        dataAsMap.put("id_ambito", Integer.toString(id_ambito));
        dataAsMap.put("id_muro", Integer.toString(id_muro));
        dataAsMap.put("id_evento", Integer.toString(id_evento));
        
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
	 * @return the id_muro
	 */
	@XmlElement(name = "id_muro")
	public Integer getId_muro() {
		return id_muro;
	}

	/**
	 * @param id_muro the id_muro to set
	 */
	public void setId_muro(Integer id_muro) {
		this.id_muro = id_muro;
	}

	/**
	 * @return the content
	 */
	@XmlElement(name = "content")
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the id_evento
	 */
	@XmlElement(name = "id_evento")
	public Integer getId_evento() {
		return id_evento;
	}

	/**
	 * @param id_evento the id_evento to set
	 */
	public void setId_evento(Integer id_evento) {
		this.id_evento = id_evento;
	}

	
	
}
