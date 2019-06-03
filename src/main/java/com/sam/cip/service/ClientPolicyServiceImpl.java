package com.sam.cip.service;

import com.sam.cip.model.ErrorRecord;
import com.sam.cip.model.Record;
import com.sam.cip.model.Records;
import com.sam.cip.model.Status;
import com.sam.cip.repository.ClientPolicyRepository;
import com.sam.cip.request.RecordsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientPolicyServiceImpl implements ClientPolicyService {

    private final ClientPolicyRepository clientPolicyRepo;
    private final static String MIN_INSURE_AMT_ERROR = "The monthly premium cannot be negative and at most be 2,5% of the insured amount";
    private final static String UNIQUE_ERROR = "The ClientID and PolicyID should be unique";

    @Autowired
    public ClientPolicyServiceImpl(final ClientPolicyRepository clientPolicyRepoVal) {
        this.clientPolicyRepo = clientPolicyRepoVal;
    }

    @Override
    public Status submitPolicyRecords(RecordsRequest recordsJson) {
        List<ErrorRecord> errors = new ArrayList<>();
        for (Record record : recordsJson.getRecords().getRecord()) {
            ErrorRecord errorRecord = validateAndSavePolicyRecord(record);
            if(errorRecord!=null){
                errors.add(errorRecord);
            }
        }
        if (errors.size() < 1) {
            return new Status("SUCCESS", null);
        } else {
            return new Status("FAIL", errors);
        }
    }


    @Override
    public Records getRecords() {
        Records recordsObj = new Records();
        recordsObj.setRecord((List<Record>) clientPolicyRepo.findAll());
        return recordsObj;
    }

    @Override
    public Status savePolicyRecord(Record record) {
        String status = "FAIL";
        List<ErrorRecord> errors = null;
        ErrorRecord errorRecord = validateAndSavePolicyRecord(record);
        if (errorRecord == null) status = "SUCCESS";
        else {
            errors = new ArrayList<>();
            errors.add(errorRecord);
        }
        return new Status(status, errors);
    }

    public ErrorRecord validateAndSavePolicyRecord(Record record) {
        ErrorRecord errorRecord = null;
        if (record.getMonthlyPremium() > 0 && record.getMonthlyPremium() <= 0.025 * record.getInsuredAmount()) {
            try {
                clientPolicyRepo.save(record);
            } catch (DataIntegrityViolationException e) {
                errorRecord = getErrorStatusMessage(record, UNIQUE_ERROR);
            }
        } else {
            errorRecord = getErrorStatusMessage(record, MIN_INSURE_AMT_ERROR);
        }
        return errorRecord;
    }

    private ErrorRecord getErrorStatusMessage(Record record, String errorMsg) {
        return new ErrorRecord(record.getClientID(), record.getPolicyID(), errorMsg);
    }

}
