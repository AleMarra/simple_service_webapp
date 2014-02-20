package com.fiuba.taller.activity.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "delete-group-request")
public class DeleteGroupRequest {

    private Integer id;
    private String usernameCoordinador;
    private Integer idGrupo;


    public DeleteGroupRequest(){}

    public DeleteGroupRequest(Integer id, String usernameCoordinador, Integer idGrupo)
    {
        super();
        this.setId(id);
        this.setUsernameCoordinador(usernameCoordinador);
        this.setIdGrupo(idGrupo);

    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "id" + k + id + p;
        result += "usernameCoordinador" + k + usernameCoordinador + p;
        result += "idGrupo" + k + idGrupo;

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
        dataAsForm.param("idGrupo", Integer.toString(idGrupo));

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("id", Integer.toString(id));
        dataAsMap.put("usernameCoordinador", usernameCoordinador);
        dataAsMap.put("idGrupo", Integer.toString(idGrupo));

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
	@XmlElement(name = "idGrupo")
    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

}