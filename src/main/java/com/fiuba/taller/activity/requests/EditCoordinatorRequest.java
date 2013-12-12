package com.fiuba.taller.service.requests;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "edit-coordinator-request")
public class EditCoordinatorRequest {

    private Integer activityId;
    private String usernameNewCoordinator;


    public EditCoordinatorRequest(){}

    public EditCoordinatorRequest(Integer activityId, String usernameNewCoordinator)
    {
        super();
        this.setActivityId(activityId);
        this.setUsernameNewCoordinator(usernameNewCoordinator);

    }

    private String toReadable(String keyValSeparator, String propSeparator) {
        String result = "";
        String k = keyValSeparator;
        String p = propSeparator;

        result += "activityId" + k + activityId + p;
        result += "usernameNewCoordinator" + k + usernameNewCoordinator;

        return result;
    }

    @Override
    public String toString(){
        return toReadable("=", "&");
    }

    public String toJSON(){
        return "{" + toReadable(": ", ", ") + "}";
    }

    public Form toForm(){
        Form dataAsForm = new Form();

        dataAsForm.param("activityId", Integer.toString(activityId));
        dataAsForm.param("usernameNewCoordinator", usernameNewCoordinator);

        return dataAsForm;
    }

    public Map<String, String> toMap(){
        Map<String, String> dataAsMap = new HashMap<String, String>();

        dataAsMap.put("activityId", Integer.toString(activityId));
        dataAsMap.put("usernameNewCoordinator", usernameNewCoordinator);

        return dataAsMap;
    }

	@XmlElement(name = "activityId")
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
	@XmlElement(name = "usernameNewCoordinator")
    public String getUsernameNewCoordinator() {
        return usernameNewCoordinator;
    }

    public void setUsernameNewCoordinator(String usernameNewCoordinator) {
        this.usernameNewCoordinator = usernameNewCoordinator;
    }

}