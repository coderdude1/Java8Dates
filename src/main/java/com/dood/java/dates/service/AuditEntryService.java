package com.dood.java.dates.service;

import com.dood.java.dates.model.AuditEntry;
import com.dood.java.dates.repository.AuditEntryRepository;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    public AuditEntry create(LocalDate localDate, LocalDateTime localDateTime, Date date, String auditEntry, Instant instant) {
        AuditEntry auditEntryObj = createAuditEntry(localDate, localDateTime, date, auditEntry, instant);
        create(auditEntryObj);
        return auditEntryObj;
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
        LocalDateTime now = LocalDateTime.now();
        Random random = new SecureRandom();
        return createAuditEntry(LocalDate.now(), now, new Date(), "This is an audit string for " + now,
            Instant.now());
    }

    private AuditEntry createAuditEntry(LocalDate localDate, LocalDateTime localDateTime, Date date, String auditEntry,
                                        Instant instant) {
        return new AuditEntry()
                .setDate(date)
                .setLocalDate(localDate)
                .setLocalDateTime(localDateTime)
                .setAuditEntry(auditEntry)
                .setInstant(instant);
    }
}
