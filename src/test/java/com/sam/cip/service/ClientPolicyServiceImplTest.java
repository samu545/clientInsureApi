package com.sam.cip.service;

import com.sam.cip.model.Record;
import com.sam.cip.model.Records;
import com.sam.cip.model.Status;
import com.sam.cip.repository.ClientPolicyRepository;
import com.sam.cip.request.RecordsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientPolicyServiceImplTest {

    @Mock
    ClientPolicyRepository repository;

    private ClientPolicyServiceImpl service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.service = new ClientPolicyServiceImpl(repository);
    }


    @Test
    public void submitPolicyRecords() {
        Status status = service.submitPolicyRecords(mockRecordRequestObj());
        assertNotNull(status);
        assertNotNull(status.getState());
        assertEquals(status.getState(), "FAIL");
        assertNotNull(status.getErrors());
        assertEquals(status.getErrors().size(), 1);
        assertEquals(status.getErrors().get(0).getClientID(), 1125);
        assertEquals(status.getErrors().get(0).getPolicyID(), "policy-3");
        assertEquals(status.getErrors().get(0).getMessage(), "The monthly premium cannot be negative and at most be 2,5% of the insured amount");
    }

    @Test
    public void submitPolicyRecordsHappyFlow() {
        Status status = service.submitPolicyRecords(mockRecordRequestObjHappy());
        assertNotNull(status);
        assertNotNull(status.getState());
        assertEquals(status.getState(), "SUCCESS");
        assertNull(status.getErrors());
    }


    @Test
    public void getRecords() {
        when(repository.findAll()).thenReturn(mockRecordObj());
        Records records = service.getRecords();
        assertNotNull(records);
        assertEquals(records.getRecord().size(), 3);
    }

    @Test
    public void savePolicyRecord() {
        Status status = service.savePolicyRecord(mockRecordObj().get(0));
        assertNotNull(status);
        assertEquals(status.getState(), "SUCCESS");
    }

    @Test
    public void savePolicyRecordError() {
        Status status2 = service.savePolicyRecord(mockRecordObj().get(2));
        assertNotNull(status2);
        assertEquals(status2.getState(), "FAIL");
    }

    private RecordsRequest mockRecordRequestObj() {
        Records records = new Records();
        records.setRecord(mockRecordObj());
        return new RecordsRequest(records);
    }
    // collecting happy record requesst objects
    private RecordsRequest mockRecordRequestObjHappy() {
        Records records = new Records();
        records.setRecord(mockRecordObj().subList(0,1));
        return new RecordsRequest(records);
    }

    private List<Record> mockRecordObj() {
        List<Record> records = new ArrayList<>();
        Record record1 = new Record(1123, "policy-1", 5700, 70, 0.30f, "xyz-1123");
        records.add(record1);
        Record record2 = new Record(1124, "policy-2", 5700, 70, 0.30f, "xyz-1124");
        records.add(record2);
        Record record3 = new Record(1125, "policy-3", 5700, 700, 0.30f, "xyz-1125");
        records.add(record3);
        return records;
    }
}