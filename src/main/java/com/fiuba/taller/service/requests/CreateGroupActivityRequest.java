package com.fiuba.taller.service.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "create-individual-activity-request")
public class CreateGroupActivityRequest {
	
	private String name;
	private String description;
	//TODO no estaba definido si los coordinadores iban o no
	private String coordinator;
	private Integer idAmbitoSuperior;
	private Integer idActividadSuperior;
	private String fechaInicio;
	private String fechaFin;
	private boolean gruposExclusivos;

	
    public CreateGroupActivityRequest(){}
    
    public CreateGroupActivityRequest(
    							String name,
					    		String description,
					    		String coordinator,
					    		Integer idAmbitoSuperior,
					    		Integer idActividadSuperior,
					    		String fechaInicio,
					    		String fechaFin,
					    		boolean gruposExclusivos)
    {
        super();
        this.setName(name);
	    this.setDescription(description);
	    this.setCordinator(coordinator);
	    this.setAmbitoSuperior(idAmbitoSuperior);
	    this.setActividadSuperior(idActividadSuperior);
	    this.setFechaInicio(fechaInicio);
	    this.setFechaFin(fechaFin);
	    this.setGruposExclusivos(gruposExclusivos);
    }
    
    @Override 
    public String toString(){
    	String urlEncoded ="";
    	
    	urlEncoded += "name="+name+"&";
    	urlEncoded += "description="+description+"&";
    	urlEncoded += "coordinator="+coordinator+"&";
    	urlEncoded += "idAmbitoSuperior="+idAmbitoSuperior+"&";
    	urlEncoded += "idActividadSuperior="+idActividadSuperior+"&";
    	urlEncoded += "fechaInicio="+fechaInicio+"&";
    	urlEncoded += "fechaFin="+fechaFin;
    	urlEncoded += "gruposExclusivos="+gruposExclusivos;
    	
    	return urlEncoded;
    }
    
    public Form toForm(){
    	
    	Form dataAsForm = new Form() ;
    	
    	dataAsForm = dataAsForm.param("name", name)
			    			   .param("description", description)
			    			   .param("coordinator", coordinator)
			    			   .param("idAmbitoSuperior", Integer.toString(idAmbitoSuperior))
			    			   .param("idActividadSuperior", Integer.toString(idActividadSuperior))
			    			   .param("fechaInicio", fechaInicio)
			    			   .param("fechaFin", fechaFin)
    						   .param("gruposExclusivos", gruposExclusivos);
    	return dataAsForm; 
    }
    
    public Map<String, String> toMap(){
    	
    	Map<String, String> dataAsMap= new HashMap<String, String>() ;
    	
    	dataAsMap.put("name", name);
    	dataAsMap.put("description", description);
    	dataAsMap.put("coordinator", coordinator);
    	dataAsMap.put("idAmbitoSuperior", Integer.toString(idAmbitoSuperior));
    	dataAsMap.put("idActividadSuperior", Integer.toString(idActividadSuperior));
    	dataAsMap.put("fechaInicio", fechaInicio);
    	dataAsMap.put("fechaFin", fechaFin);
    	dataAsMap.put("gruposExclusivos", gruposExclusivos);
    	    	
    	return dataAsMap; 
    }

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "coordinator")
	public String getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}

	@XmlElement(name = "idAmbitoSuperior")
	public Integer getAmbitoSuperior() {
		return idAmbitoSuperior;
	}

	public void setAmbitoSuperior(Integer idAmbitoSuperior) {
		this.idAmbitoSuperior = idAmbitoSuperior;
	}

	@XmlElement(name = "idActividadSuperior")
	public Integer getActividadSuperior() {
		return idActividadSuperior;
	}

	public void setActividadSuperior(String idActividadSuperior) {
		this.idActividadSuperior = idActividadSuperior;
	}

	@XmlElement(name = "fechaInicio")
	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@XmlElement(name = "fechaFin")
	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@XmlElement(name = "gruposExclusivos")
	public boolean getGruposExclusivos() {
		return gruposExclusivos;
	}

	public void setGruposExclusivos(boolean gruposExclusivos) {
		this.gruposExclusivos = gruposExclusivos;
	}
}
