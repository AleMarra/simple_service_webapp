package com.fiuba.taller.service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "security-response")
public class SecurityResponse {
	
    private boolean success;
    private String reason;
    private String fullReason;
    private String authToken;

    public SecurityResponse(){}
    
    public SecurityResponse(boolean success, String reason) {
        super();
        this.success= success;
        this.reason = reason;
        this.authToken = "";
    }
    
    public SecurityResponse(boolean success, String reason, String authToken) {
        super();
        this.success= success;
        this.reason = reason;
        this.authToken = authToken;
    }


    @XmlElement(name = "success")
    public boolean getSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }

    
    @XmlElement(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
   
    @XmlElement(name = "authToken")
    public String getAuthToken() {
        return authToken;
    }
    
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }



    @XmlElement(name = "fullReason")
    public String getFullReason() {
        return fullReason;
    }

    public void setFullReason(String fullReason) {
        this.fullReason = fullReason;
    }



    @Override 
    public String toString(){
    	return String.format("SecurityResponse - Successful: %s ; Reason: %s ; AuthToken: %s",
    					success, reason, authToken);
    }
}
