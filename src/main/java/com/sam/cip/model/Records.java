package com.sam.cip.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "records")
public class Records implements Serializable {

    private static final long serialVersionUID = -7315805986113739181L;

    private List<Record> record;

    public List<Record> getRecord() {
        return record;
    }

    public void setRecord(List<Record> record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Records [record = " + record + "]";
    }
}