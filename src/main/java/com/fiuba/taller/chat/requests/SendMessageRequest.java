package com.fiuba.taller.chat.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class SendMessageRequest {

	private Integer id_chat;
	private Integer id_member;
	private String message;
	
    public SendMessageRequest(){}
	
    public SendMessageRequest(Integer id_chat)
    {
        super();
        this.setId_chat(id_chat);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "id_chat" + k + id_chat + p;
        result += "id_member" + k + id_member + p;
        result += "message" + k + message + p;
        
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

        dataAsForm.param("id_chat", Integer.toString(id_chat));
        dataAsForm.param("id_member",  Integer.toString(id_member));
        dataAsForm.param("message", message);
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("id_chat", Integer.toString(id_chat));
        dataAsMap.put("id_member",  Integer.toString(id_member));
        dataAsMap.put("message", message);

        return dataAsMap;
    }

	@XmlElement(name = "id_chat")
	public Integer getId_chat() {
		return id_chat;
	}

	public void setId_chat(Integer id_ambito) {
		this.id_chat = id_ambito;
	}

	/**
	 * @return the message
	 */
	@XmlElement(name = "message")
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the id_member
	 */
	@XmlElement(name = "id_member")
	public Integer getId_member() {
		return id_member;
	}

	/**
	 * @param id_member the id_member to set
	 */
	public void setId_member(Integer id_member) {
		this.id_member = id_member;
	}


    
}
