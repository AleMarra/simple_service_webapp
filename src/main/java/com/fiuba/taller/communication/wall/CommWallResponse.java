package com.fiuba.taller.communication.wall;

public class CommWallResponse {
    private boolean success;
    private String reason;
    private int[] events;
    private String wall;
    private int id_event;
    private String event;
    		
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
	 * @return the events
	 */
	public int[] getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(int[] events) {
		this.events = events;
	}

	/**
	 * @return the wall
	 */
	public String getWall() {
		return wall;
	}

	/**
	 * @param wall the wall to set
	 */
	public void setWall(String wall) {
		this.wall = wall;
	}

	/**
	 * @return the id_event
	 */
	public int getId_event() {
		return id_event;
	}

	/**
	 * @param id_event the id_event to set
	 */
	public void setId_event(int id_event) {
		this.id_event = id_event;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}
   
	
}