package com.fiuba.taller.communication.forum.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class MessageDeleteRequest {

	private String username;
	private Integer id_mensaje;
	private Integer id_ambito;
	private Integer id_foro;
	private Integer id_tema;
	
    public MessageDeleteRequest(){}
	
    public MessageDeleteRequest(String username, String content, String section_name, Integer id_ambito, Integer id_mensaje, Integer id_foro, Integer id_tema)
    {
        super();
        this.setUsername(username);
        this.setId_mensaje(id_mensaje);
        this.setId_ambito(id_ambito);
        this.setId_foro(id_foro);
        this.setId_tema(id_tema);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "username" + k + username + p;
        result += "id_mensaje" + k + id_mensaje + p;
        result += "id_ambito" + k + id_ambito + p;
        result += "id_foro" + k + id_foro + p;
        result += "id_tema" + k + id_tema + p;

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
        dataAsForm.param("id_ambito", Integer.toString(id_ambito));
        dataAsForm.param("id_foro", Integer.toString(id_foro));
        dataAsForm.param("id_tema", Integer.toString(id_tema));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("username", username);
        dataAsMap.put("id_mensaje", Integer.toString(id_mensaje));
        dataAsMap.put("id_ambito", Integer.toString(id_ambito));
        dataAsMap.put("id_foro", Integer.toString(id_foro));
        dataAsMap.put("id_tema", Integer.toString(id_tema));
        
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
	 * @return the id_tema
	 */
	@XmlElement(name = "id_tema")
	public Integer getId_tema() {
		return id_tema;
	}

	/**
	 * @param id_tema the id_tema to set
	 */
	public void setId_tema(Integer id_tema) {
		this.id_tema = id_tema;
	}

	/**
	 * @return the id_mensaje
	 */
	@XmlElement(name = "id_mensaje")
	public Integer getId_mensaje() {
		return id_mensaje;
	}

	/**
	 * @param id_mensaje the id_mensaje to set
	 */
	public void setId_mensaje(Integer id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

    
	
}
