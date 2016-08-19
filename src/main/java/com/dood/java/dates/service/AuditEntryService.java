package com.dood.java.dates.service;

import com.dood.java.dates.model.AuditEntry;
import com.dood.java.dates.repository.AuditEntryRepository;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class AuditEntryService {
    private static final Logger LOG = LoggerFactory.getLogger(AuditEntryService.class);

    @Autowired
    private AuditEntryRepository auditEntryRepository;

    public AuditEntry createOneNow() {
        return createRandomAuditEntry("count: 1");
    }

    public void createRandomAuditEntries(Integer count) {
        for (int i = 0; i < count; i++) {
            auditEntryRepository.save(createRandomAuditEntry("count: " + count));
        }
    }

    public void create(AuditEntry auditEntry) {
        this.auditEntryRepository.save(auditEntry);
        LOG.info("created: {}", auditEntry);
    }

    public AuditEntry get(String id) {
        return auditEntryRepository.findOne(id);
    }

    public List<AuditEntry> findAll() {
        return auditEntryRepository.findAll();
    }

    public void delete(AuditEntry auditEntry) {
        auditEntryRepository.delete(auditEntry);
    }

    public void delete(String id) {
        auditEntryRepository.delete(id);
    }

    public void deleteAll() {
        auditEntryRepository.deleteAll();
    }

    private AuditEntry createRandomAuditEntry(String auditEntry) {
        return new AuditEntry()
                .setDate(new Date())
                .setLocalDate(LocalDate.now())
                .setLocalDateTime(LocalDateTime.now())
                .setAuditEntry(auditEntry);
    }

}
