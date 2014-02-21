package com.fiuba.taller.materials.responses;

import javax.xml.bind.annotation.XmlElement;

import org.w3c.dom.Element;

public abstract class MaterialsResponse {

    protected boolean success = false;
    protected String reason = "";
    protected String authToken = "";

    public MaterialsResponse(){}
    
    public MaterialsResponse(boolean success, String reason) {
        super();
        this.success= success;
        this.reason = reason;
        this.authToken = "";
    }
    
    public MaterialsResponse(boolean success, String reason, String authToken) {
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
    
    
    @Override 
    public String toString(){
    	return String.format("MaterialsResponse - Successful: %s ; Reason: %s ; AuthToken: %s",
    					success, reason, authToken);
    }
    
    public abstract void setResourcesFromXML(Element resourcesRoot);
}
