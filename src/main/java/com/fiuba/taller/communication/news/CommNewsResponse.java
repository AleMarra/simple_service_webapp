package com.fiuba.taller.communication.news;

public class CommNewsResponse {
    private boolean success;
    private String reason;
    private int[] news; 
    private String newShow; 
    private int id_new;
    		
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

	/**
	 * @return the news
	 */
	public int[] getNews() {
		return news;
	}

	/**
	 * @param news the news to set
	 */
	public void setNews(int[] news) {
		this.news = news;
	}

	/**
	 * @return the newShow
	 */
	public String getNewShow() {
		return newShow;
	}

	/**
	 * @param newShow the newShow to set
	 */
	public void setNewShow(String newShow) {
		this.newShow = newShow;
	}

	/**
	 * @return the id_new
	 */
	public int getId_new() {
		return id_new;
	}

	/**
	 * @param id_new the id_new to set
	 */
	public void setId_new(int id_new) {
		this.id_new = id_new;
	}
    
    
}
