package com.fiuba.taller.resources.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class EliminarTablaRequest {

	private String usuario;
	private int tabla;
	
    public EliminarTablaRequest(){}
	
    public EliminarTablaRequest(String usuario, int tabla, int campo)
    {
        super();
        this.setUsuario(usuario);
        this.setTabla(tabla);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "usuario" + k + usuario + p;
        result += "tabla" + k + tabla + p;
        
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
        dataAsForm.param("tabla", Integer.toString(tabla));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("usuario", usuario);
        dataAsMap.put("tabla", Integer.toString(tabla));
        
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
	 * @return the tabla
	 */
	@XmlElement(name = "tabla")
	public int getTabla() {
		return tabla;
	}

	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(int tabla) {
		this.tabla = tabla;
	}

}