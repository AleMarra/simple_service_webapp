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
        private String tipoEscala;

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

        @XmlElement(name = "tipoEscala")
        public String getTipoEscala() {
            return tipoEscala;
        }

        public void setTipoEscala(String tipoEscala) {
            this.tipoEscala = tipoEscala;
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

//    "<WS>" +
//        "<list>" +
//            "<Actividad>" +
//                "<id>4928</id>" +
//                "<actividadId>4928</actividadId>" +
//                "<nombre>Trabajo Practico</nombre>" +
//                "<descripcion>Se lala.</descripcion>" +
//                "<tipo>Grupal Evaluable</tipo>" +
//                "<fechaInicio>1382918400</fechaInicio>" +
//                "<fechaFin>1383004800</fechaFin>" +
//                "<gruposExclusivos>false</gruposExclusivos>" +
//                "<tipoEscala>Decimal</tipoEscala>" +
//            "</Actividad>" +
//        "</list>" +
//    "</WS>";
    public void setActivityPropertiesFromXML(Element resourceRoot) {

        ActivityProperties resource = new ActivityProperties();

        resource.setId(Integer.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "id")));
        resource.setNombre(xmlHandler.getFirstElementValue(resourceRoot, "nombre"));
        resource.setDescripcion(xmlHandler.getFirstElementValue(resourceRoot, "descripcion"));
        resource.setTipo(xmlHandler.getFirstElementValue(resourceRoot, "tipo"));
        resource.setFechaInicio(new Date(Long.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "fechaInicio")) * 1000));
        if (Long.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "fechaFin")) != null)
            resource.setFechaFin(new Date(Long.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "fechaFin")) * 1000));
        if (Boolean.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "gruposExclusivos")) != null)
            resource.setGruposExclusivos(Boolean.valueOf(xmlHandler.getFirstElementValue(resourceRoot, "gruposExclusivos")));
        resource.setTipoEscala(xmlHandler.getFirstElementValue(resourceRoot, "tipoEscala"));

        this.resource = resource;

    }

}
