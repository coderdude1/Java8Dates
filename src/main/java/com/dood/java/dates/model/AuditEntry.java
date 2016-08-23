package com.dood.java.dates.model;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * The SpringData mongo integration does not currently support ZonedDateTime yet, so use this for some experiments
 * with CRUD.
 *
 * TODO add other java8 concepts like Instant, Duration, And Period.  Not sure if they can be persisted.
 * Add some biz methods on here too to do stuff with fancy date manip
 */
public class AuditEntry {

    @Id
    private String id;

    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private Date date;
    private String auditEntry;

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

    public Date getDate() {
        return date;
    }

    public AuditEntry setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getAuditEntry() {
        return auditEntry;
    }

    public AuditEntry setAuditEntry(String auditEntry) {
        this.auditEntry = auditEntry;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("localDate", localDate)
                .add("localDateTime", localDateTime)
                .add("date", date)
                .add("auditEntry", auditEntry)
                .toString();
    }
}
