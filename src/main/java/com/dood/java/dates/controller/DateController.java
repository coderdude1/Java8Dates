package com.dood.java.dates.controller;

import com.dood.java.dates.model.TimestampObject;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Api(description = "Provides various java 8 date operations")
@RestController
public class DateController {
    private static final Logger LOG = LoggerFactory.getLogger(DateController.class);

    @RequestMapping(value = "/getSome", method = RequestMethod.GET)
    public TimestampObject index() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        TimestampObject timestampObject = new TimestampObject()
                .setZonedDateTime(zonedDateTime)
                .setLocalDate(localDate)
                .setLocalDateTime(localDateTime);
        LOG.debug("index called.  Object={}", timestampObject);
        return timestampObject;
    }
}
