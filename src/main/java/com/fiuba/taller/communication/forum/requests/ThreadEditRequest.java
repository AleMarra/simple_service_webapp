package com.fiuba.taller.communication.forum.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class ThreadEditRequest {

	private String username;
	private String title;
	private String content;
	private Integer id_tema;
	private Integer id_ambito;
	private Integer id_foro;
	
    public ThreadEditRequest(){}
	
    public ThreadEditRequest(String username, String section_name, Integer id_ambito, Integer id_foro)
    {
        super();
        this.setUsername(username);
        this.setTitle(title);
        this.setContent(content);
        this.setId_tema(id_tema);
        this.setId_ambito(id_ambito);
        this.setId_foro(id_foro);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "username" + k + username + p;
        result += "title" + k + title + p;
        result += "content" + k + content + p;
        result += "id_tema" + k + id_tema + p;
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
        dataAsForm.param("title", title);
        dataAsForm.param("content", content);
        dataAsForm.param("id_tema", Integer.toString(id_tema));
        dataAsForm.param("id_ambito", Integer.toString(id_ambito));
        dataAsForm.param("id_foro", Integer.toString(id_foro));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("username", username);
        dataAsMap.put("title", title);
        dataAsMap.put("content", content);
        dataAsMap.put("id_tema", Integer.toString(id_tema));
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

    
	
}
