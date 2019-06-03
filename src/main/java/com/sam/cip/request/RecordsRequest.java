package com.sam.cip.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sam.cip.model.Records;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordsRequest {
    private Records records;

    public Records getRecords() {
        return records;
    }

    public RecordsRequest(Records records) {
        this.records = records;
    }
}
