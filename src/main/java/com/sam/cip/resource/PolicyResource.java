package com.sam.cip.resource;

import com.sam.cip.model.Record;
import com.sam.cip.model.Records;
import com.sam.cip.request.RecordsRequest;
import com.sam.cip.model.Status;
import com.sam.cip.response.StatusResponse;
import com.sam.cip.service.ClientPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*@GetMapping(path = "/{clientId}-{policyId}")
    public Record getClientPolicy(final @PathVariable long clientId, final @PathVariable long policyId) {
        return clientPolicyService.getClientPolicy(clientId, policyId);
    }*/

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
