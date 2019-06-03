package com.sam.cip.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorRecord {
    @XmlElement(name = "clientID")
    @JsonProperty(value = "clientID")
    private long clientID;
    @XmlElement(name = "policyID")
    @JsonProperty(value = "policyID")
    private String policyID;
    @XmlElement(name = "message")
    @JsonProperty(value = "message")
    private String message;

    public ErrorRecord(long clientID, String policyID, String message) {
        this.clientID = clientID;
        this.policyID = policyID;
        this.message = message;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
