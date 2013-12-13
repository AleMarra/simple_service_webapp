package com.fiuba.taller.activity.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "create-group-activity-request")
public class CreateGroupEvaluableActivityRequest {

    private String nombre;
    private String descripcion;
    private Integer idAmbitoSuperior;
    private Integer idActividadSuperior;
    private String fechaInicio;
    private String fechaFin;
    private Boolean gruposExclusivos;
    private Integer nota;


    public CreateGroupEvaluableActivityRequest(){}

    public CreateGroupEvaluableActivityRequest(String nombre, String descripcion, Integer idAmbitoSuperior, Integer idActividadSuperior, String fechaInicio, String fechaFin, Boolean gruposExclusivos, Integer nota)
    {
        super();
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setIdAmbitoSuperior(idAmbitoSuperior);
        this.setIdActividadSuperior(idActividadSuperior);
        this.setFechaInicio(fechaInicio);
        this.setFechaFin(fechaFin);
        this.setGruposExclusivos(gruposExclusivos);
        this.setNota(nota);

    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "nombre" + k + nombre + p;
        result += "descripcion" + k + descripcion + p;
        result += "idAmbitoSuperior" + k + idAmbitoSuperior + p;
        result += "idActividadSuperior" + k + idActividadSuperior + p;
        result += "fechaInicio" + k + fechaInicio + p;
        result += "fechaFin" + k + fechaFin + p;
        result += "gruposExclusivos" + k + gruposExclusivos + p;
        result += "nota" + k + nota;

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

        dataAsForm.param("nombre", nombre);
        dataAsForm.param("descripcion", descripcion);
        dataAsForm.param("idAmbitoSuperior", Integer.toString(idAmbitoSuperior));
        dataAsForm.param("idActividadSuperior", Integer.toString(idActividadSuperior));
        dataAsForm.param("fechaInicio", fechaInicio);
        dataAsForm.param("fechaFin", fechaFin);
        dataAsForm.param("gruposExclusivos", Boolean.toString(gruposExclusivos));
        dataAsForm.param("nota", Integer.toString(nota));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("nombre", nombre);
        dataAsMap.put("descripcion", descripcion);
        dataAsMap.put("idAmbitoSuperior", Integer.toString(idAmbitoSuperior));
        dataAsMap.put("idActividadSuperior", Integer.toString(idActividadSuperior));
        dataAsMap.put("fechaInicio", fechaInicio);
        dataAsMap.put("fechaFin", fechaFin);
        dataAsMap.put("gruposExclusivos", Boolean.toString(gruposExclusivos));
        dataAsMap.put("nota", Integer.toString(nota));

        return dataAsMap;
    }

	@XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	@XmlElement(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
	@XmlElement(name = "idAmbitoSuperior")
    public Integer getIdAmbitoSuperior() {
        return idAmbitoSuperior;
    }

    public void setIdAmbitoSuperior(Integer idAmbitoSuperior) {
        this.idAmbitoSuperior = idAmbitoSuperior;
    }
	@XmlElement(name = "idActividadSuperior")
    public Integer getIdActividadSuperior() {
        return idActividadSuperior;
    }

    public void setIdActividadSuperior(Integer idActividadSuperior) {
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
    public Boolean getGruposExclusivos() {
        return gruposExclusivos;
    }

    public void setGruposExclusivos(Boolean gruposExclusivos) {
        this.gruposExclusivos = gruposExclusivos;
    }
    
	@XmlElement(name = "nota")
    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

}