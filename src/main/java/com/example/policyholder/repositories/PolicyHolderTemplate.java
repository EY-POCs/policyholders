package com.example.policyholder.repositories;
import com.example.policyholder.models.CustomError;
import com.example.policyholder.models.Info;
import com.example.policyholder.models.PolicyHolder;
import com.example.policyholder.models.PolicyHolderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolicyHolderTemplate {
    @Autowired
    MongoTemplate mongoTemplate;

    private MongoTemplate mongoTemplate() { return this.mongoTemplate; }

    public List<PolicyHolderDTO> findPolicyHolderAllFields(String policyHolderId,
                                                           String name,
                                                           String address,
                                                           String creditCardNumber){
        Query query = new Query();
        if(policyHolderId != null){
            query.addCriteria(Criteria.where("_id").is(policyHolderId.toUpperCase()));
        }

        if(name != null){
            query.addCriteria(Criteria.where("name").is(name));
        }

        if(address != null){
            query.addCriteria(Criteria.where("address").is(address));
        }

        if(creditCardNumber != null){
            query.addCriteria(Criteria.where("creditCardNumber").is(creditCardNumber));
        }

        return mongoTemplate.find(query, PolicyHolderDTO.class);
    }

    public PolicyHolder deletedById(String id){
        Query query = new Query();
        PolicyHolder policyHolder = new PolicyHolder();
        query.addCriteria(Criteria.where("_id").is(id));
        Info info = new Info();
        try {
            PolicyHolderDTO ph = mongoTemplate.findAndRemove(query, PolicyHolderDTO.class);
            info.setMessage("Policy holder is deleted succesfuly");
            info.setSource("deleteById");
            policyHolder.setInfo(info);
            policyHolder.setPolicyHolderId(ph.getPolicyHolderId());
            policyHolder.setAddress(ph.getAddress());
            policyHolder.setCreditCardNumber(ph.getCreditCardNumber());
            policyHolder.setName(ph.getName());
            return policyHolder;
        }
        catch (Exception e){
            CustomError error = new CustomError();
            error.setMessage("error happened while deleting policyholder \n"+ e.getMessage());
            error.setType("deleteById");
            policyHolder.setError(error);
            return policyHolder;
        }
    }

    public PolicyHolder updatePolicyHolder(PolicyHolderDTO policyHolderDTO){
        Query query = new Query();
        PolicyHolder policyHolder = new PolicyHolder();
        query.addCriteria(Criteria.where("_id").is(policyHolderDTO.getPolicyHolderId()));
        Info info = new Info();
        Update update = new Update();

        if(policyHolderDTO.getName() != null){
            query.addCriteria(Criteria.where("name").is(policyHolderDTO.getName()));
        }

        if(policyHolderDTO.getCreditCardNumber() != null){
            query.addCriteria(Criteria.where("creditCardNumber").is(policyHolderDTO.getCreditCardNumber()));
        }

        if(policyHolderDTO.getAddress() != null){
            query.addCriteria(Criteria.where("address").is(policyHolderDTO.getAddress()));
        }

        try {
            mongoTemplate.updateFirst(query, update, PolicyHolderDTO.class);
            info.setMessage("Policy holder is updated succesfuly");
            info.setSource("updatePolicyHolder");
            policyHolder.setInfo(info);
            return policyHolder;
        }
        catch (Exception e){
            CustomError error = new CustomError();
            error.setMessage("error happened while updating policyholder \n"+ e.getMessage());
            error.setType("updatePolicyHolder");
            policyHolder.setError(error);
            return policyHolder;
        }
    }
}