package com.fiuba.taller.materials;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recurso")
public class MaterialsResource {
	
	public static enum ResourceTypes {ARCHIVO, LINK, ENCUESTA, LISTA, SIMPLE}; 

	public static ResourceTypes castToType(String type){
		
		if( type.toUpperCase().equals(ResourceTypes.ARCHIVO.name())) 
			return ResourceTypes.ARCHIVO;
		
		if( type.toUpperCase().equals(ResourceTypes.LINK.name())) 
			return ResourceTypes.LINK;
		
		if( type.toUpperCase().equals(ResourceTypes.ENCUESTA.name())) 
			return ResourceTypes.ENCUESTA;
		
		if( type.toUpperCase().equals(ResourceTypes.LISTA.name())) 
			return ResourceTypes.LISTA;
		
		return null;
	}
	
	protected Integer recursoId;
	protected String tipo; 
	protected Integer ambitoId;
	protected String descripcion;
	
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
		this.tipo = tipo;
	}
	
	@XmlElement(name = "ambitoId")
	public Integer getAmbitoId() {
		return ambitoId;
	}
	public void setAmbitoId(Integer ambitoId) {
		this.ambitoId = ambitoId;
	}
	
	@XmlElement(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    @Override 
    public String toString(){
    	return String.format("MaterialsResource: recurso: %s; ambito: %s; tipo: %s; desc: %s",
    						 recursoId, ambitoId, tipo, descripcion);
    }

    
}
