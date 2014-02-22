package com.fiuba.taller.activity.responses;

import com.fiuba.taller.BaseResponse;
import com.fiuba.taller.materials.MaterialsResource;
import com.fiuba.taller.utils.DateAdapter;
import com.fiuba.taller.utils.XmlHandler;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

public class GetPropertiesResponse extends BaseResponse {
    private static final XmlHandler xmlHandler = new XmlHandler();

    @XmlRootElement(name = "actividad")
    public static class ActivityProperties {
        private Integer id;
        private String nombre;
        private String descripcion;
        private String tipo;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date fechaInicio;
        @XmlJavaTypeAdapter(DateAdapter.class)
        private Date fechaFin;
        private Boolean gruposExclusivos;
        private String escala;

        public ActivityProperties() {}

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

        @XmlElement(name = "tipo")
        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
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

    private ActivityProperties resource = new ActivityProperties();

    public GetPropertiesResponse() {}

    public GetPropertiesResponse(boolean success, String reason) {
        super(success, reason, "");
    }

    public GetPropertiesResponse(boolean success, String reason, String authToken) {
        super(success, reason, authToken);
    }

    @XmlElement(name = "properties")
    public ActivityProperties getResource() {
        return resource;
    }

    public void setResources(ActivityProperties resource) {
        this.resource = resource;
    }

//    <Actividad>
//        <Id>4928</Id>
//        <Nombre>Trabajo Practico</Nombre>
//        <Descripcion>Se lala.</Descripcion>
//        <Tipo>Grupal Evaluable</Tipo>
//        <FechaInicio>1382918400</FechaInicio>
//        <FechaFin>1383004800</FechaFin>
//        <GruposExclusivos>false</GruposExclusivos>
//        <Escala>Decimal</Escala>
//    </Actividad>
    public void setActivityPropertiesFromXML(Element resourceRoot) {

        ActivityProperties resource = new ActivityProperties();

        resource.setId(Integer.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "Id")));
        resource.setNombre(xmlHandler.getFirstElementValue(resourceRoot, "Nombre"));
        resource.setDescripcion(xmlHandler.getFirstElementValue(resourceRoot, "Descripcion"));
        resource.setTipo(xmlHandler.getFirstElementValue(resourceRoot, "Tipo"));
        resource.setFechaInicio(new Date(Long.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "FechaInicio")) * 1000));
        resource.setFechaFin(new Date(Long.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "FechaFin")) * 1000));
        resource.setGruposExclusivos(Boolean.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "GruposExclusivos")));
        resource.setEscala(xmlHandler.getFirstElementValue(resourceRoot, "Escala"));

        this.resource = resource;

    }

}
