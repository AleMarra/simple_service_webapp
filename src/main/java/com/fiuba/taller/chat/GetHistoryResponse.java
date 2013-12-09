package com.fiuba.taller.chat;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "chat-response")
public class GetHistoryResponse {
	
    private boolean success;
    private String reason;
    private String authToken;
    private int lastCallTimestamp;
	private int id_chat;
    private Mensaje[] messages;

    public GetHistoryResponse(){}
    
    public GetHistoryResponse(boolean success, String reason) {
        super();
        this.success= success;
        this.reason = reason;
        this.authToken = "";
    }
    
    public GetHistoryResponse(boolean success, String reason, String authToken) {
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
    
    public int getLastCallTimestamp() {
		return lastCallTimestamp;
	}

	public void setLastCallTimestamp(int lastCallTimestamp) {
		this.lastCallTimestamp = lastCallTimestamp;
	}

	public int getId_chat() {
		return id_chat;
	}

	public void setId_chat(int id_chat) {
		this.id_chat = id_chat;
	}

	public Mensaje[] getMessages() {
		return messages;
	}

	public void setMessages(Mensaje[] messages) {
		this.messages = messages;
	}

	@Override 
    public String toString(){
    	return String.format("ChatResponse - Successful: %s ; Reason: %s ; AuthToken: %s",
    					success, reason, authToken);
    }
    
}
