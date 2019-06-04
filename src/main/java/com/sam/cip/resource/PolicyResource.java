package com.sam.cip.resource;

import com.sam.cip.model.Record;
import com.sam.cip.model.Records;
import com.sam.cip.request.RecordsRequest;
import com.sam.cip.model.Status;
import com.sam.cip.response.StatusResponse;
import com.sam.cip.service.ClientPolicyService;
import com.sam.cip.utils.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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

    @PostMapping(value = "/upload", consumes = {"multipart/form-data", "application/x-www-form-urlencoded"})
    public void uploadSimple(@RequestParam("file") MultipartFile file) throws IOException {
        List<Record> records = CsvUtil.read(Record.class, file.getInputStream());
        clientPolicyService.submitCsvListRecords(records);
    }

    @PostMapping(path = "/submit",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public StatusResponse submitPolicyRequestCheck(@RequestBody RecordsRequest recordsReqJson) {
        StatusResponse response = new StatusResponse();
        response.setStatus(clientPolicyService.submitPolicyRecords(recordsReqJson));
        return response;
    }

    @PostMapping(path = "/save",
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public Status savePolicy(@RequestBody Record recordJson) {
        return clientPolicyService.savePolicyRecord(recordJson);
    }
}
