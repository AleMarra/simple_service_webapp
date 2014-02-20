package com.fiuba.taller.materials.requests;

import java.util.HashMap;

import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuba.taller.materials.MaterialsResource;

import static com.fiuba.taller.materials.MaterialsResource.ResourceTypes;

@XmlRootElement(name = "get-resources-request")
public class GetResourceRequest{
	
	private Integer recursoId;
	private String tipo; 
	
	public GetResourceRequest(){};
	
	public GetResourceRequest(int recursoId, String type){
		
		this.recursoId = recursoId;
		this.setTipo(type);
		
	};
	
    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "recursoId" + k + recursoId.toString() + p;
        result += "tipo" + k + tipo + p;

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
        
        dataAsForm.param("recursoId", Integer.toString(recursoId));
        dataAsForm.param("tipo", tipo);

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("recursoId", Integer.toString(recursoId));
        dataAsMap.put("tipo", tipo);
      
        return dataAsMap;
    }
    
	@XmlElement(name = "recursoId")
	public Integer getRecursoId() {
		return recursoId;
	}

	public void setRecursoId(Integer recursoId) {
		this.recursoId = recursoId;
	}

	@XmlElement(name = "tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		
		if(MaterialsResource.castToType(tipo) != null)
			this.tipo = tipo.toLowerCase();
		else
			this.tipo = "unknown";
		
	}
}

