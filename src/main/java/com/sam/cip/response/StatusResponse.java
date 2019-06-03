package com.sam.cip.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sam.cip.model.Status;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusResponse {
    Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
