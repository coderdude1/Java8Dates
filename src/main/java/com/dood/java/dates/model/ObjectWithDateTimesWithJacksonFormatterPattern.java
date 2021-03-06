package com.dood.java.dates.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class ObjectWithDateTimesWithJacksonFormatterPattern {
    //on demand formatting pattern
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime localDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private ZonedDateTime zonedDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date utilDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Instant instant;


    public LocalDate getLocalDate() {
        return localDate;
    }

    public ObjectWithDateTimesWithJacksonFormatterPattern setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public ObjectWithDateTimesWithJacksonFormatterPattern setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public ObjectWithDateTimesWithJacksonFormatterPattern setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
        return this;
    }

    public Date getUtilDate() {
        return utilDate;
    }

    public ObjectWithDateTimesWithJacksonFormatterPattern setUtilDate(Date utilDate) {
        this.utilDate = utilDate;
        return this;
    }

    public Instant getInstant() {
        return instant;
    }

    public ObjectWithDateTimesWithJacksonFormatterPattern setInstant(Instant instant) {
        this.instant = instant;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("localDate", localDate)
                .add("localDateTime", localDateTime)
                .add("zonedDateTime", zonedDateTime)
                .add("utilDate", utilDate)
                .add("instant", instant)
                .toString();
    }
}
