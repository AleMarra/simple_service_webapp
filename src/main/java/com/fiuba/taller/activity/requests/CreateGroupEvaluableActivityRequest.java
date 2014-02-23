package com.fiuba.taller.activity.requests;

import com.fiuba.taller.utils.DateAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "create-group-activity-request")
public class CreateGroupEvaluableActivityRequest {

    private String nombre;
    private String descripcion;
    private Integer idAmbitoSuperior;
    private Integer idActividadSuperior;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicio;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFin;
    private Boolean gruposExclusivos;
    private String tipoEscala;


    public CreateGroupEvaluableActivityRequest(){}

    public CreateGroupEvaluableActivityRequest(String nombre, String descripcion, Integer idAmbitoSuperior, Integer idActividadSuperior, Date fechaInicio, Date fechaFin, Boolean gruposExclusivos, String tipoEscala)
    {
        super();
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setIdAmbitoSuperior(idAmbitoSuperior);
        this.setIdActividadSuperior(idActividadSuperior);
        this.setFechaInicio(fechaInicio);
        this.setFechaFin(fechaFin);
        this.setGruposExclusivos(gruposExclusivos);
        this.setTipoEscala(tipoEscala);

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
        result += "tipoEscala" + k + tipoEscala;

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
        dataAsForm.param("fechaInicio", Long.toString(fechaInicio.getTime() / 1000));
        dataAsForm.param("fechaFin", Long.toString(fechaFin.getTime() / 1000));
        dataAsForm.param("gruposExclusivos", Boolean.toString(gruposExclusivos));
        dataAsForm.param("tipoEscala", tipoEscala);

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("nombre", nombre);
        dataAsMap.put("descripcion", descripcion);
        dataAsMap.put("idAmbitoSuperior", Integer.toString(idAmbitoSuperior));
        dataAsMap.put("idActividadSuperior", Integer.toString(idActividadSuperior));
        dataAsMap.put("fechaInicio", Long.toString(fechaInicio.getTime() / 1000));
        dataAsMap.put("fechaFin", Long.toString(fechaFin.getTime() / 1000));
        dataAsMap.put("gruposExclusivos", Boolean.toString(gruposExclusivos));
        dataAsMap.put("tipoEscala", tipoEscala);

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
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
	@XmlElement(name = "fechaFin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
	@XmlElement(name = "gruposExclusivos")
    public Boolean getGruposExclusivos() {
        return gruposExclusivos;
    }

    public void setGruposExclusivos(Boolean gruposExclusivos) {
        this.gruposExclusivos = gruposExclusivos;
    }
    
	@XmlElement(name = "tipoEscala")
    public String getTipoEscala() {
        return tipoEscala;
    }

    public void setTipoEscala(String tipoEscala) {
        this.tipoEscala = tipoEscala;
    }

}