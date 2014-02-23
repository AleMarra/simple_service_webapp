package com.fiuba.taller.resources.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class CrearFilaRequest {

	private String usuario;
	private int IdTabla;
	private String[] valores;
	
	public CrearFilaRequest(){}
	
	public CrearFilaRequest(String usuario, int IdTabla, String[] valores)
	{
		super();
	}
	
    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "usuario" + k + usuario + p;
        result += "IdTabla" + k + IdTabla + p;
        result += "valores" + k + valores + p;
        
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

        dataAsForm.param("usuario", usuario);
        dataAsForm.param("IdTabla", Integer.toString(IdTabla));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("usuario", usuario);
        dataAsMap.put("IdTabla", Integer.toString(IdTabla));
        
        return dataAsMap;
    }

	/**
	 * @return the usuario
	 */
	@XmlElement(name = "usuario")
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the idTabla
	 */
	@XmlElement(name = "IdTabla")
	public int getIdTabla() {
		return IdTabla;
	}

	/**
	 * @param idTabla the idTabla to set
	 */
	public void setIdTabla(int idTabla) {
		IdTabla = idTabla;
	}

	/**
	 * @return the valores
	 */
	@XmlElement(name = "valores")
	public String[] getValores() {
		return valores;
	}

	/**
	 * @param valores the valores to set
	 */
	public void setValores(String[] valores) {
		this.valores = valores;
	}
    
    
}
