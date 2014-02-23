package com.fiuba.taller.resources.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class CrearTablaRequest {

	private String[] nombres;
	private boolean[] permisos;
	private String[] tipos;
	private String usuario;
	
    public CrearTablaRequest(){}
	
    public CrearTablaRequest(String[] nombre, boolean[] permisos, String[] tipos, String usuario)
    {
        super();
        this.setNombres(nombre);
        this.setPermisos(permisos);
        this.setTipos(tipos);
        this.setUsuario(usuario);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "nombres" + k + nombres + p;
        result += "permisos" + k + permisos + p;
        result += "tipos" + k + tipos + p;
        result += "usuario" + k + usuario + p;
        
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
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("usuario", usuario);
        
        return dataAsMap;
    }

	/**
	 * @return the nombres
	 */
	@XmlElement(name = "nombres")
	public String[] getNombres() {
		return nombres;
	}

	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String[] nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the permisos
	 */
	@XmlElement(name = "permisos")
	public boolean[] getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(boolean[] permisos) {
		this.permisos = permisos;
	}

	/**
	 * @return the tipos
	 */
	@XmlElement(name = "tipos")
	public String[] getTipos() {
		return tipos;
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(String[] tipos) {
		this.tipos = tipos;
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
    
}