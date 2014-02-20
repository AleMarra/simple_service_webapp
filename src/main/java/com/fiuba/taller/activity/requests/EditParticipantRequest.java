package com.fiuba.taller.activity.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "edit-participant-request")
public class EditParticipantRequest {

    private Integer id;
    private String usernameCoordinador;
    private String usernameParticipante;


    public EditParticipantRequest(){}

    public EditParticipantRequest(Integer id, String usernameCoordinador, String usernameParticipante)
    {
        super();
        this.setId(id);
        this.setUsernameCoordinador(usernameCoordinador);
        this.setUsernameParticipante(usernameParticipante);

    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "id" + k + id + p;
        result += "usernameCoordinador" + k + usernameCoordinador + p;
        result += "usernameParticipante" + k + usernameParticipante;

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
        dataAsForm.param("usernameParticipante", usernameParticipante);

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("id", Integer.toString(id));
        dataAsMap.put("usernameCoordinador", usernameCoordinador);
        dataAsMap.put("usernameParticipante", usernameParticipante);

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
	@XmlElement(name = "usernameParticipante")
    public String getUsernameParticipante() {
        return usernameParticipante;
    }

    public void setUsernameParticipante(String usernameParticipante) {
        this.usernameParticipante = usernameParticipante;
    }

}