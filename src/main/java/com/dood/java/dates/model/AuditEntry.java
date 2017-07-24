package com.dood.java.dates.model;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

import java.time.*;
import java.util.Date;

/**
 * The SpringData mongo integration does not currently support ZonedDateTime yet, so use this for some experiments
 * with CRUD.
 *
 * TODO add other java8 concepts like Instant, Duration, And Period.  Not sure if they can be persisted.
 * Add some biz methods on here too to do stuff with fancy javaUtilDate manip
 */
public class AuditEntry {

    @Id
    private String id;

    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Date javaUtilDate;
    private String auditEntry;
    private Instant instant;
//    private Duration duration;//not supported by springdata mongo


    public void now() {
        localDate = LocalDate.now();
        localDateTime = LocalDateTime.now();
        javaUtilDate = new Date();
        auditEntry = "NOW";
        instant = Instant.now();
    }

    public String getId() {
        return id;
    }

    public AuditEntry setId(String id) {
        this.id = id;
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public AuditEntry setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public AuditEntry setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public Date getJavaUtilDate() {
        return javaUtilDate;
    }

    public AuditEntry setJavaUtilDate(Date javaUtilDate) {
        this.javaUtilDate = javaUtilDate;
        return this;
    }

    public String getAuditEntry() {
        return auditEntry;
    }

    public AuditEntry setAuditEntry(String auditEntry) {
        this.auditEntry = auditEntry;
        return this;
    }

    public Instant getInstant() {
        return instant;
    }

    public AuditEntry setInstant(Instant instant) {
        this.instant = instant;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("localDate", localDate)
                .add("localDateTime", localDateTime)
                .add("javaUtilDate", javaUtilDate)
                .add("auditEntry", auditEntry)
                .add("instant", instant)
                .toString();
    }
}
