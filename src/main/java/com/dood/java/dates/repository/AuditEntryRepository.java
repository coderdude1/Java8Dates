package com.dood.java.dates.repository;

import com.dood.java.dates.model.AuditEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditEntryRepository extends MongoRepository<AuditEntry, String> {
}
