package com.fiuba.taller.chat;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chat-response")
public class GetMembersResponse {
	
    private boolean success;
    private String reason;
    private String authToken;
    private Miembro[] members;

    public GetMembersResponse(){}
    
    public GetMembersResponse(boolean success, String reason) {
        super();
        this.success= success;
        this.reason = reason;
        this.authToken = "";
    }
    
    public GetMembersResponse(boolean success, String reason, String authToken) {
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
    
    public Miembro[] getMembers() {
		return members;
	}

	public void setMembers(Miembro[] members) {
		this.members = members;
	}

	@Override 
    public String toString(){
    	return String.format("ChatResponse - Successful: %s ; Reason: %s ; AuthToken: %s",
    					success, reason, authToken);
    }
    
}
