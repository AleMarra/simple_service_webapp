package com.fiuba.taller.resources.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forum-request")
public class ModificarFilaRequest {

	private String usuario;
	private int IdTabla;
	private int numFila;
	private String[] valoresNuevos;
	
    public ModificarFilaRequest(){}
	
    public ModificarFilaRequest(String usuario, int IdTabla, int numFila, String[] valoresNuevos)
    {
        super();
        this.setUsuario(usuario);
        this.setIdTabla(IdTabla);
        this.setNumFila(numFila);
        this.setValoresNuevos(valoresNuevos);
    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "usuario" + k + usuario + p;
        result += "IdTabla" + k + IdTabla + p;
        result += "numFila" + k + numFila + p;
        result += "valoresNuevos" + k + valoresNuevos + p;
        
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
        dataAsForm.param("numFila", Integer.toString(numFila));
        
        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("usuario", usuario);
        dataAsMap.put("IdTabla", Integer.toString(IdTabla));
        dataAsMap.put("numFila", Integer.toString(numFila));
        
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
	 * @return the numFila
	 */
	@XmlElement(name = "numFila")
	public int getNumFila() {
		return numFila;
	}

	/**
	 * @param numFila the numFila to set
	 */
	public void setNumFila(int numFila) {
		this.numFila = numFila;
	}

	/**
	 * @return the valoresNuevos
	 */
	@XmlElement(name = "valoresNuevos")
	public String[] getValoresNuevos() {
		return valoresNuevos;
	}

	/**
	 * @param valoresNuevos the valoresNuevos to set
	 */
	public void setValoresNuevos(String[] valoresNuevos) {
		this.valoresNuevos = valoresNuevos;
	}


    
}