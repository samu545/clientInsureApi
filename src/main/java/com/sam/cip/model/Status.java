package com.sam.cip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "status")
public final class Status {
    @XmlElement(name = "state")
    private final String state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @XmlElement(name = "errors")
    private final List<ErrorRecord> errors;

    public Status(String stateVal, List<ErrorRecord> errorRecordVal) {
        this.state = stateVal;
        this.errors = errorRecordVal;
    }

    public String getState() {
        return state;
    }

    public List<ErrorRecord> getErrors() {
        return errors;
    }
}
