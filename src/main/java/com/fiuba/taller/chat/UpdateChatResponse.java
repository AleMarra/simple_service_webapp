package com.fiuba.taller.chat;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chat-response")
public class UpdateChatResponse {
	
    private boolean success;
    private String reason;
    private String authToken;
    private Mensaje[] messages;
    private Integer[] members;

    public UpdateChatResponse(){}
    
    public UpdateChatResponse(boolean success, String reason) {
        super();
        this.success= success;
        this.reason = reason;
        this.authToken = "";
    }
    
    public UpdateChatResponse(boolean success, String reason, String authToken) {
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

	public Mensaje[] getMessages() {
		return messages;
	}

	public void setMessages(Mensaje[] messages) {
		this.messages = messages;
	}

    
    public Integer[] getMembers() {
		return members;
	}

	public void setMembers(Integer[] members) {
		this.members = members;
	}

	@Override 
    public String toString(){
    	return String.format("ChatResponse - Successful: %s ; Reason: %s ; AuthToken: %s",
    					success, reason, authToken);
    }
    
}
