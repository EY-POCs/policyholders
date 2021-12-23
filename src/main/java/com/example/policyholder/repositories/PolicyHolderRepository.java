package com.example.policyholder.repositories;

import com.example.policyholder.models.PolicyHolderDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PolicyHolderRepository extends MongoRepository<PolicyHolderDTO, String> {
    @Query("{id: '?0'}")
    PolicyHolderDTO findPolicyHolderById(String id);
}

