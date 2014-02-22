package com.fiuba.taller.activity.requests;

import com.fiuba.taller.utils.DateAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "add-activity-request")
public class AddActivityRequest {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String usernameCoordinadorSuperior;
    private Integer idAmbitoSuperior;
    private Integer idActividadSuperior;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicio;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFin;
    private Boolean gruposExclusivos;
    private String escala;


    public AddActivityRequest(){}

    public AddActivityRequest(Integer id, String nombre, String descripcion, String usernameCoordinadorSuperior, Integer idAmbitoSuperior, Integer idActividadSuperior, Date fechaInicio, Date fechaFin, Boolean gruposExclusivos, String escala)
    {
        super();
        this.setId(id);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setUsernameCoordinadorSuperior(usernameCoordinadorSuperior);
        this.setIdAmbitoSuperior(idAmbitoSuperior);
        this.setIdActividadSuperior(idActividadSuperior);
        this.setFechaInicio(fechaInicio);
        this.setFechaFin(fechaFin);
        this.setGruposExclusivos(gruposExclusivos);
        this.setEscala(escala);

    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "id" + k + id + p;
        result += "nombre" + k + nombre + p;
        result += "descripcion" + k + descripcion + p;
        result += "usernameCoordinadorSuperior" + k + usernameCoordinadorSuperior + p;
        result += "idAmbitoSuperior" + k + idAmbitoSuperior + p;
        result += "idActividadSuperior" + k + idActividadSuperior + p;
        result += "fechaInicio" + k + fechaInicio + p;
        result += "fechaFin" + k + fechaFin + p;
        result += "gruposExclusivos" + k + gruposExclusivos + p;
        result += "escala" + k + escala;

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
        dataAsForm.param("nombre", nombre);
        dataAsForm.param("descripcion", descripcion);
        dataAsForm.param("usernameCoordinadorSuperior", usernameCoordinadorSuperior);
        dataAsForm.param("idAmbitoSuperior", Integer.toString(idAmbitoSuperior));
        dataAsForm.param("idActividadSuperior", Integer.toString(idActividadSuperior));
        dataAsForm.param("fechaInicio", Long.toString(fechaInicio.getTime()));
        dataAsForm.param("fechaFin", Long.toString(fechaFin.getTime()));
        dataAsForm.param("gruposExclusivos", Boolean.toString(gruposExclusivos));
        dataAsForm.param("escala", escala);

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("id", Integer.toString(id));
        dataAsMap.put("nombre", nombre);
        dataAsMap.put("descripcion", descripcion);
        dataAsMap.put("usernameCoordinadorSuperior", usernameCoordinadorSuperior);
        dataAsMap.put("idAmbitoSuperior", Integer.toString(idAmbitoSuperior));
        dataAsMap.put("idActividadSuperior", Integer.toString(idActividadSuperior));
        dataAsMap.put("fechaInicio", Long.toString(fechaInicio.getTime()));
        dataAsMap.put("fechaFin", Long.toString(fechaFin.getTime()));
        dataAsMap.put("gruposExclusivos", Boolean.toString(gruposExclusivos));
        dataAsMap.put("escala", escala);

        return dataAsMap;
    }

	@XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
	@XmlElement(name = "usernameCoordinadorSuperior")
    public String getUsernameCoordinadorSuperior() {
        return usernameCoordinadorSuperior;
    }

    public void setUsernameCoordinadorSuperior(String usernameCoordinadorSuperior) {
        this.usernameCoordinadorSuperior = usernameCoordinadorSuperior;
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
	@XmlElement(name = "escala")
    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

}