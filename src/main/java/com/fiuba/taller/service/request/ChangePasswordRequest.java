package com.fiuba.taller.service.request;

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
        result += "newPassword" + k + newPassword;

        return result;
    }

    @Override
    public String toString(){
        return toReadable("=", "&");
    }

    public String toJSON(){
        return "{" + toReadable(": ", ", ") + "}";
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