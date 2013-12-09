package com.fiuba.taller.communication;

public class CommunicationResponse {
    private boolean success;
    private String reason;
    private String authToken;

	/**
	 * @return the authToken
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * @param authToken the authToken to set
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
    
    
}
