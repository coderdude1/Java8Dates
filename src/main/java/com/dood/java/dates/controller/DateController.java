package com.dood.java.dates.controller;

import com.dood.java.dates.model.TimestampObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@RestController
public class DateController {

    @RequestMapping("/getSome")
    public TimestampObject index() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        TimestampObject timestampObject = new TimestampObject()
                .setZonedDateTime(zonedDateTime)
                .setLocalDate(localDate)
                .setLocalDateTime(localDateTime);

        return timestampObject;
    }
}
