package com.example.policyholder.services;

import com.example.policyholder.models.PolicyHolder;
import com.example.policyholder.models.PolicyHolderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPolicyHolderService {
    List<PolicyHolderDTO> getPolicyHolderDetails(String policyHolderId,
                                                 String name,
                                                 String address,
                                                 String creditCardNumber);
    PolicyHolder createPolicyHolder(PolicyHolderDTO policyHolder);

    PolicyHolder updatePolicyHolder(PolicyHolderDTO policyHolder);

    PolicyHolder deletePolicyHolder(String id);

    List<PolicyHolderDTO> getAllPolicyHolders();
}
