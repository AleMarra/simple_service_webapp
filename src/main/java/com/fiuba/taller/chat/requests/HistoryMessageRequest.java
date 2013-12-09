package com.fiuba.taller.chat.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class HistoryMessageRequest {

	private Integer id_chat;
	private Integer id_member;
	
    public HistoryMessageRequest(){}
	
    public HistoryMessageRequest(Integer id_chat)
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

        dataAsForm.param("id_ambito", Integer.toString(id_chat));
        dataAsForm.param("id_member", Integer.toString(id_member));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("id_ambito", Integer.toString(id_chat));
        dataAsMap.put("id_member", Integer.toString(id_member));
        
        return dataAsMap;
    }

	@XmlElement(name = "id_chat")
	public Integer getId_chat() {
		return id_chat;
	}

	public void setId_chat(Integer id_ambito) {
		this.id_chat = id_ambito;
	}
	
	@XmlElement(name = "id_member")
	public Integer getId_member() {
		return id_member;
	}

	public void setId_member(Integer id_member) {
		this.id_member = id_member;
	}
    
}
