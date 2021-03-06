package com.dood.java.dates.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Basic pojo to experiment with db persistence and json serialization to clients
 *
 * SPringData Mongo does not currently support the ZonedDateTime for CRUD (it let me persist it (see README.md) but not
 * do a findAll).
 */
public class TimestampObject {
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private ZonedDateTime zonedDateTime;
    private Instant instant;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void now() {
        localDate = LocalDate.now();
        localDateTime = LocalDateTime.now();
        zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        instant = Instant.now();
    }


    public TimestampObject setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public TimestampObject setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public TimestampObject setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("localDate", localDate)
                .add("localDateTime", localDateTime)
                .add("zonedDateTime", zonedDateTime)
                .toString();
    }
}
