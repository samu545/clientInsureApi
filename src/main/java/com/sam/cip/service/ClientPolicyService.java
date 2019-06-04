package com.sam.cip.service;


import com.sam.cip.model.Record;
import com.sam.cip.model.Records;
import com.sam.cip.request.RecordsRequest;
import com.sam.cip.model.Status;

import java.util.List;

public interface ClientPolicyService {
    Status submitPolicyRecords(RecordsRequest recordsJson);

    Records getRecords();

    Status savePolicyRecord(Record recordJson);

    Status submitCsvListRecords(List<Record> records);
}
