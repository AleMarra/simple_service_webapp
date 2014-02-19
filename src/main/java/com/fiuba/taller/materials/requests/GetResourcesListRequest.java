package com.fiuba.taller.materials.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "get-resources-request")
public class GetResourcesListRequest{
	
	private Integer ambitoId;
	
	public GetResourcesListRequest(){};
	
	public GetResourcesListRequest(int ambitoId){
		this.ambitoId = ambitoId;
	};
	
    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "ambitoId" + k + ambitoId.toString() + p;
       
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
        
        dataAsForm.param("ambitoId", Integer.toString(ambitoId));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("ambitoId", Integer.toString(ambitoId));
      
        return dataAsMap;
    }
    
	@XmlElement(name = "ambitoId")
	public Integer getAmbitoId() {
		return ambitoId;
	}

	public void setAmbitoId(Integer ambitoId) {
		this.ambitoId = ambitoId;
	}

}
