package com.fiuba.taller.resources.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class ModificarCampoRequest {

	private String usuario;
	private int tabla;
	private int campo;
	private String nuevoNombre;
	private String nuevoTipo;
	private boolean permiteNulo;
	
    public ModificarCampoRequest(){}
	
    public ModificarCampoRequest(String usuario)
    {
        super();
        this.setUsuario(usuario);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "usuario" + k + usuario + p;
        result += "tabla" + k + tabla + p;
        result += "campo" + k + campo + p;
        result += "nuevoNombre" + k + nuevoNombre + p;
        result += "nuevoTipo" + k + nuevoTipo + p;
        result += "permiteNulo" + k + permiteNulo + p;
        
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
        dataAsForm.param("nuevoNombre", nuevoNombre);
        dataAsForm.param("nuevoTipo", nuevoTipo);
        dataAsForm.param("permiteNulo", Boolean.toString(permiteNulo));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("usuario", usuario);
        dataAsMap.put("tabla", Integer.toString(tabla));
        dataAsMap.put("campo", Integer.toString(campo));
        dataAsMap.put("nuevoNombre", nuevoNombre);
        dataAsMap.put("nuevoTipo", nuevoTipo);
        dataAsMap.put("permiteNulo", Boolean.toString(permiteNulo));
        
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

	/**
	 * @return the nuevoNombre
	 */
	@XmlElement(name = "nuevoNombre")
	public String getNuevoNombre() {
		return nuevoNombre;
	}

	/**
	 * @param nuevoNombre the nuevoNombre to set
	 */
	public void setNuevoNombre(String nuevoNombre) {
		this.nuevoNombre = nuevoNombre;
	}

	/**
	 * @return the nuevoTipo
	 */
	@XmlElement(name = "nuevoTipo")
	public String getNuevoTipo() {
		return nuevoTipo;
	}

	/**
	 * @param nuevoTipo the nuevoTipo to set
	 */
	public void setNuevoTipo(String nuevoTipo) {
		this.nuevoTipo = nuevoTipo;
	}

	/**
	 * @return the permiteNulo
	 */
	@XmlElement(name = "permiteNulo")
	public boolean isPermiteNulo() {
		return permiteNulo;
	}

	/**
	 * @param permiteNulo the permiteNulo to set
	 */
	public void setPermiteNulo(boolean permiteNulo) {
		this.permiteNulo = permiteNulo;
	}



}