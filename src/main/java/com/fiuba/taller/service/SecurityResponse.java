package com.fiuba.taller.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SecurityResponse {
    private boolean successful;
    private String reason;

    public boolean isSuccessful() {
        return successful;
    }

    public String getReason() {
        return reason;
    }
}
