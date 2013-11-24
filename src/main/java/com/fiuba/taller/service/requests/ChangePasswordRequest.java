package com.fiuba.taller.service.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "change-password-request")
public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;


    public ChangePasswordRequest(){}

    public ChangePasswordRequest(String oldPassword, String newPassword)
    {
        super();
        this.setOldPassword(oldPassword);
        this.setNewPassword(newPassword);

    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "oldPassword" + k + oldPassword + p;
        result += "newPassword" + k + newPassword + p;

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

        dataAsForm.param("oldPassword", oldPassword);
        dataAsForm.param("newPassword", newPassword);

        return dataAsForm;
    }

    public Map<String, String> toMap(){
    Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("oldPassword", oldPassword);
        dataAsMap.put("newPassword", newPassword);

        return dataAsMap;
    }

	@XmlElement(name = "oldPassword")
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
	@XmlElement(name = "newPassword")
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}