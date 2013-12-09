package com.fiuba.taller.communication.forum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comm-response")
public class CommForumResponse {
	private boolean success;
	private String reason;
	private int idSection;
	private int idSubForum;
	private int idThread;
	private int idMessage;
	private String index;
	private String subForum;
	private String thread;
	private String message;
	private int[] messages;
	
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
	
	@XmlElement(name = "id-section")
	public int getIdSection() {
		return idSection;
	}
	
	public void setIdSection(int idSection) {
		this.idSection = idSection;
	}
	
	@XmlElement(name = "id-subforum")
	public int getIdSubForum() {
		return idSubForum;
	}
	
	public void setIdSubForum(int idSubForum) {
		this.idSubForum = idSubForum;
	}
	
	@XmlElement(name = "id-thread")
	public int getIdThread() {
		return idThread;
	}
	
	public void setIdThread(int idThread) {
		this.idThread = idThread;
	}
	
	@XmlElement(name = "id-message")
	public int getIdMessage() {
		return idMessage;
	}
	
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	
	@XmlElement(name = "index")
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	@XmlElement(name = "subforum")
	public String getSubForum() {
		return subForum;
	}
	
	public void setSubForum(String subForum) {
		this.subForum = subForum;
	}
	
	@XmlElement(name = "thread")
	public String getThread() {
		return thread;
	}
	
	public void setThread(String thread) {
		this.thread = thread;
	}
	
	@XmlElement(name = "message")
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@XmlElement(name = "id-messages")
	public int[] getMessages() {
		return messages;
	}
	
	public void setMessages(int[] messages) {
		this.messages = messages;
	}

}
