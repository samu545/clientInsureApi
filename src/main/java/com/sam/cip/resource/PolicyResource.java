package com.sam.cip.resource;

import com.sam.cip.model.Record;
import com.sam.cip.model.Records;
import com.sam.cip.model.Status;
import com.sam.cip.request.RecordsRequest;
import com.sam.cip.response.StatusResponse;
import com.sam.cip.service.ClientPolicyService;
import com.sam.cip.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyResource {

    private final ClientPolicyService clientPolicyService;

    @Autowired
    public PolicyResource(final ClientPolicyService clientPolicyService) {
        this.clientPolicyService = clientPolicyService;
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public Records getPolicyRecords() {
        return clientPolicyService.getRecords();
    }

    @PostMapping(path = "/submit",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public StatusResponse submitPolicyRequestCheck(@RequestBody RecordsRequest recordsReqJson) {
        return toStatusResponse(clientPolicyService.submitPolicyRecords(recordsReqJson));
    }

    @PostMapping(path = "/save",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public Status savePolicy(@RequestBody Record recordJson) {
        return clientPolicyService.savePolicyRecord(recordJson);
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data", "application/x-www-form-urlencoded"})
    public StatusResponse uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {
        List<Record> records = CsvUtil.read(Record.class, file.getInputStream());
        return toStatusResponse(clientPolicyService.submitCsvListRecords(records));
    }


    private StatusResponse toStatusResponse(Status status) {
        StatusResponse response = new StatusResponse();
        response.setStatus(status);
        return response;
    }
}
