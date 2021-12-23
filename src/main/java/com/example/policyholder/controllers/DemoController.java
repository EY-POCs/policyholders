package com.example.policyholder.controllers;

import com.example.policyholder.models.CustomError;
import com.example.policyholder.models.PolicyHolder;
import com.example.policyholder.models.PolicyHolderDTO;
import com.example.policyholder.services.PolicyHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    PolicyHolderService policyHolderService;

    @PutMapping(value="/policyholder", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PolicyHolder updatePolicyHolder(@RequestBody PolicyHolderDTO policyHolderDTO){
        if(policyHolderDTO == null){
            return null;
        }
        return policyHolderService.updatePolicyHolder(policyHolderDTO);
    }

    @PostMapping(value="/policyholder", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PolicyHolder createPolicyHolder(@RequestBody PolicyHolderDTO policyHolderDTO){
        if(policyHolderDTO == null){
            CustomError error = new CustomError();
            error.setMessage("Policy holder cannot be null");
            PolicyHolder ph = new PolicyHolder();
            ph.setError(error);
            return ph;
        }
        return policyHolderService.createPolicyHolder(policyHolderDTO);
    }

    @DeleteMapping(value="/policyholder/{policyHolderId}")
    public PolicyHolder deletePolicyHolder(@PathVariable(required = true) String policyHolderId){
        if(policyHolderId == null){
            CustomError error = new CustomError();
            error.setMessage("PolicyHolderId cannot be null");
            PolicyHolder ph = new PolicyHolder();
            ph.setError(error);
            return ph;
        }
        return policyHolderService.deletePolicyHolder(policyHolderId.toUpperCase());
    }

    @GetMapping(value="/policyholders")
    public List<PolicyHolderDTO> getAllPolicyHolders(){
        return policyHolderService.getAllPolicyHolders();
    }

    @GetMapping(value="/policyholderdetails")
    public List<PolicyHolderDTO> filterPolicyHolderByAnyField(@RequestParam(required = false) String policyHolderId,
                                                              @RequestParam(required = false) String name,
                                                              @RequestParam(required = false) String address,
                                                              @RequestParam(required = false) String creditCardNumber){
        return policyHolderService.getPolicyHolderDetails(policyHolderId, name,
                address, creditCardNumber);
    }
}
