package com.fiuba.taller.activity.requests;

import com.fiuba.taller.utils.DateAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "edit-activity-request")
public class EditActivityRequest {

    private Integer id;
    private String usernameCoordinador;
    private String nombre;
    private String descripcion;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicio;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFin;
    private Boolean gruposExclusivos;


    public EditActivityRequest(){}

    public EditActivityRequest(Integer id, String usernameCoordinador, String nombre, String descripcion, Date fechaInicio, Date fechaFin, Boolean gruposExclusivos)
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
        dataAsForm.param("fechaInicio", Long.toString(fechaInicio.getTime()));
        dataAsForm.param("fechaFin", Long.toString(fechaFin.getTime()));
        dataAsForm.param("gruposExclusivos", Boolean.toString(gruposExclusivos));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("id", Integer.toString(id));
        dataAsMap.put("usernameCoordinador", usernameCoordinador);
        dataAsMap.put("nombre", nombre);
        dataAsMap.put("descripcion", descripcion);
        dataAsMap.put("fechaInicio", Long.toString(fechaInicio.getTime()));
        dataAsMap.put("fechaFin", Long.toString(fechaFin.getTime()));
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

}