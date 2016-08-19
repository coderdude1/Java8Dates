package com.dood.java.dates.model;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Basic pojo to experiment with db persistence and json serialization to clients
 */
public class TimestampObject {
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private ZonedDateTime zonedDateTime;

    public LocalDate getLocalDate() {
        return localDate;
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
