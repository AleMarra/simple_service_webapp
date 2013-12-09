package com.fiuba.taller.communication.news.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class NewsEdit {

	private String username;
	private String title;
	private String content;
	private Integer id_ambito;
	private Integer id_cartelera;
	private Integer id_noticia;
	
    public NewsEdit(){}
	
    public NewsEdit(String username,Integer id_ambito)
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
        result += "title" + k + title + p;
        result += "content" + k + content + p;
        result += "id_ambito" + k + id_ambito + p;
        result += "id_cartelera" + k + id_cartelera + p;
        result += "id_noticia" + k + id_noticia + p;

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
        dataAsForm.param("title", title);
        dataAsForm.param("content", content);
        dataAsForm.param("id_ambito", Integer.toString(id_ambito));
        dataAsForm.param("id_cartelera", Integer.toString(id_cartelera));
        dataAsForm.param("id_noticia", Integer.toString(id_noticia));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("username", username);
        dataAsMap.put("id_ambito", Integer.toString(id_ambito));
        dataAsMap.put("id_cartelera", Integer.toString(id_cartelera));
        
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
	 * @return the id_cartelera
	 */
	@XmlElement(name = "id_cartelera")
	public Integer getId_cartelera() {
		return id_cartelera;
	}

	/**
	 * @param id_cartelera the id_cartelera to set
	 */
	public void setId_cartelera(Integer id_cartelera) {
		this.id_cartelera = id_cartelera;
	}

	/**
	 * @return the title
	 */
	@XmlElement(name = "title")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the id_noticia
	 */
	@XmlElement(name = "id_noticia")
	public Integer getId_noticia() {
		return id_noticia;
	}

	/**
	 * @param id_noticia the id_noticia to set
	 */
	public void setId_noticia(Integer id_noticia) {
		this.id_noticia = id_noticia;
	}
	
	
	
}
