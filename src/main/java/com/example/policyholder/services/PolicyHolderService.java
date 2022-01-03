package com.example.policyholder.services;

import com.example.policyholder.models.CustomError;
import com.example.policyholder.models.Info;
import com.example.policyholder.models.KeyvaultCredentials;
import com.example.policyholder.models.PolicyHolder;
import com.example.policyholder.models.PolicyHolderDTO;
import com.example.policyholder.repositories.PolicyHolderRepository;
import com.example.policyholder.repositories.PolicyHolderTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHolderService implements IPolicyHolderService {

    // base classs for basic queries
    @Autowired
    PolicyHolderRepository policyHolderRepo;

    // base classs for custom queries
    @Autowired
    PolicyHolderTemplate policyHolderTemplate;

    @Autowired
    ConfigurationServiceImpl configurationServiceImpl;

    private final String KEYVAULT_CREDENTIAL_CACHE_NAME = "keyvaultCredentials";
    private final String KEYVAULT_CREDENTIAL_CACHE_KEY = "saveKeyVaultCredentialsInCache";

    public KeyvaultCredentials getKeyvaultCredentialFromCache(){
        KeyvaultCredentials keyvaultCredentials = new KeyvaultCredentials();
        try {
            keyvaultCredentials = configurationServiceImpl.getConfigValue(
                KEYVAULT_CREDENTIAL_CACHE_NAME, KEYVAULT_CREDENTIAL_CACHE_KEY, KeyvaultCredentials.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return keyvaultCredentials;
    }

    @Override
    public List<PolicyHolderDTO> getPolicyHolderDetails(String policyHolderId,
                                                        String name,
                                                        String address,
                                                        String creditCardNumber){
        return policyHolderTemplate.findPolicyHolderAllFields(policyHolderId, name, address, creditCardNumber);
    }

    @Override
    public List<PolicyHolderDTO> getAllPolicyHolders(){
        return policyHolderRepo.findAll();
    }

    @Override
    public PolicyHolder createPolicyHolder(PolicyHolderDTO policyHolderDTO){
        PolicyHolder ph = new PolicyHolder();
        Info info = new Info();
        CustomError error = new CustomError();
        if(policyHolderRepo.existsById((policyHolderDTO.getPolicyHolderId()))){
            error.setMessage("A policy holder with id "+ policyHolderDTO.getPolicyHolderId()
                    + " is already added");
            error.setType("createPolicyHolder");
            ph.setError(error);
            return ph;
        }

        try {
            policyHolderRepo.save(policyHolderDTO);
            info.setMessage("the Policy holder is added succesfully");
            info.setSource("createPolicyHolder");
            ph.setInfo(info);
            return ph;
        }
        catch (Exception e) {
            error.setMessage("error happened while creating policyholder \n" + e.getMessage());
            error.setType("createPolicyholder");
            ph.setError(error);
            return ph;
        }
    }

    @Override
    public PolicyHolder updatePolicyHolder(PolicyHolderDTO policyHolderDTO){
        PolicyHolder ph = new PolicyHolder();
        CustomError error = new CustomError();
        if(!policyHolderRepo.existsById((policyHolderDTO.getPolicyHolderId()))){
            error.setMessage("A policy holder with id "+ policyHolderDTO.getPolicyHolderId()
                    + " does not exist, please make sure its inserted");
            error.setType("updatePolicyHolder");
            ph.setError(error);
            return ph;
        }
        return policyHolderTemplate.updatePolicyHolder(policyHolderDTO);
    }

    @Override
    public PolicyHolder deletePolicyHolder(String id){
        PolicyHolder ph = new PolicyHolder();
        CustomError error = new CustomError();
        if(!policyHolderRepo.existsById((id))){
            error.setMessage("A policy holder with id "+ id
                    + " does not exist, please make sure its inserted");
            error.setType("deletePolicyHolder");
            ph.setError(error);
            return ph;
        }
        return policyHolderTemplate.deletedById(id);
    }
}
