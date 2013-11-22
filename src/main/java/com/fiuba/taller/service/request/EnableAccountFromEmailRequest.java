package com.fiuba.taller.service.request;

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

        result += "enabledToken" + k + enabledToken + p;

        return result;
    }

    @Override
    public String toString(){
        return toReadable("=", "&");
    }

    public String toJSON(){
        return "{" + toReadable(": ", ", ") + "}";
    }

	@XmlElement(name = "enabledToken")
    public String getEnabledToken() {
        return enabledToken;
    }

    public void setEnabledToken(String enabledToken) {
        this.enabledToken = enabledToken;
    }

}