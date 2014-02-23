package com.fiuba.taller.resources.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class EliminarCampoRequest {

	private String usuario;
	private int tabla;
	private int campo;
	
    public EliminarCampoRequest(){}
	
    public EliminarCampoRequest(String usuario, int tabla, int campo)
    {
        super();
        this.setUsuario(usuario);
        this.setTabla(tabla);
        this.setCampo(campo);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "usuario" + k + usuario + p;
        result += "tabla" + k + tabla + p;
        result += "campo" + k + campo + p;
        
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
        dataAsForm.param("campo", Integer.toString(campo));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("usuario", usuario);
        dataAsMap.put("tabla", Integer.toString(tabla));
        dataAsMap.put("campo", Integer.toString(campo));
        
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

	/**
	 * @return the campo
	 */
	@XmlElement(name = "campo")
	public int getCampo() {
		return campo;
	}

	/**
	 * @param campo the campo to set
	 */
	public void setCampo(int campo) {
		this.campo = campo;
	}



}