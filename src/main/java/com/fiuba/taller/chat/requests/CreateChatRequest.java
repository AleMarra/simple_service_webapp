package com.fiuba.taller.chat.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class CreateChatRequest {

	private Integer id_ambito;
	private Integer id_member;
	
    public CreateChatRequest(){}
	
    public CreateChatRequest(Integer id_ambito, Integer id_member)
    {
        super();
        this.setId_ambito(id_ambito);
        this.setId_member(id_member);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "id_ambito" + k + id_ambito + p;
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

        dataAsForm.param("id_ambito", Integer.toString(id_ambito));
        dataAsForm.param("id_member", Integer.toString(id_member));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("id_ambito", Integer.toString(id_ambito));
        dataAsMap.put("id_member", Integer.toString(id_member));
        
        return dataAsMap;
    }

	/**
	 * @return the id_ambito
	 */
	@XmlElement(name = "id_ambito")
	public Integer getId_ambito() {
		return id_ambito;
	}

	/**
	 * @return the id_member
	 */
	public Integer getId_member() {
		return id_member;
	}

	/**
	 * @param id_member the id_member to set
	 */
	@XmlElement(name = "id_member")
	public void setId_member(Integer id_member) {
		this.id_member = id_member;
	}

	/**
	 * @param id_ambito the id_ambito to set
	 */
	public void setId_ambito(Integer id_ambito) {
		this.id_ambito = id_ambito;
	}


    
}
