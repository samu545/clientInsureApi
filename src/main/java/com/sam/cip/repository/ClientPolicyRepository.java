package com.sam.cip.repository;

import com.sam.cip.model.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPolicyRepository extends CrudRepository<Record, Long> {
}
