package com.fiuba.taller.service.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "enable-account-from-email-request")
public class EnableAccountFromEmailRequest {

    private String enabledToken;


    public EnableAccountFromEmailRequest(){}

    public EnableAccountFromEmailRequest(String enabledToken)
    {
        super();
        this.setEnabledToken(enabledToken);

    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "enabledToken" + k + enabledToken;

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

        dataAsForm.param("enabledToken", enabledToken);

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("enabledToken", enabledToken);

        return dataAsMap;
    }

	@XmlElement(name = "enabledToken")
    public String getEnabledToken() {
        return enabledToken;
    }

    public void setEnabledToken(String enabledToken) {
        this.enabledToken = enabledToken;
    }

}