package com.fiuba.taller.activity.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "edit-activity-request")
public class EditActivityRequest {

    private Integer id;
    private String usernameCoordinador;
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private Boolean gruposExclusivos;


    public EditActivityRequest(){}

    public EditActivityRequest(Integer id, String usernameCoordinador, String nombre, String descripcion, String fechaInicio, String fechaFin, Boolean gruposExclusivos)
    {
        super();
        this.setId(id);
        this.setUsernameCoordinador(usernameCoordinador);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setFechaInicio(fechaInicio);
        this.setFechaFin(fechaFin);
        this.setGruposExclusivos(gruposExclusivos);

    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "id" + k + id + p;
        result += "usernameCoordinador" + k + usernameCoordinador + p;
        result += "nombre" + k + nombre + p;
        result += "descripcion" + k + descripcion + p;
        result += "fechaInicio" + k + fechaInicio + p;
        result += "fechaFin" + k + fechaFin + p;
        result += "gruposExclusivos" + k + gruposExclusivos;

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

        dataAsForm.param("id", Integer.toString(id));
        dataAsForm.param("usernameCoordinador", usernameCoordinador);
        dataAsForm.param("nombre", nombre);
        dataAsForm.param("descripcion", descripcion);
        dataAsForm.param("fechaInicio", fechaInicio);
        dataAsForm.param("fechaFin", fechaFin);
        dataAsForm.param("gruposExclusivos", Boolean.toString(gruposExclusivos));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("id", Integer.toString(id));
        dataAsMap.put("usernameCoordinador", usernameCoordinador);
        dataAsMap.put("nombre", nombre);
        dataAsMap.put("descripcion", descripcion);
        dataAsMap.put("fechaInicio", fechaInicio);
        dataAsMap.put("fechaFin", fechaFin);
        dataAsMap.put("gruposExclusivos", Boolean.toString(gruposExclusivos));

        return dataAsMap;
    }

	@XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	@XmlElement(name = "usernameCoordinador")
    public String getUsernameCoordinador() {
        return usernameCoordinador;
    }

    public void setUsernameCoordinador(String usernameCoordinador) {
        this.usernameCoordinador = usernameCoordinador;
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

}