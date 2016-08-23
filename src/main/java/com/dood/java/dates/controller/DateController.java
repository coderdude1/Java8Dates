package com.dood.java.dates.controller;

import com.dood.java.dates.model.TimestampObject;
import com.dood.java.dates.service.TimestampObjectService;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Api(description = "Provides various java 8 date operations")
@RestController
public class DateController {
    private static final Logger LOG = LoggerFactory.getLogger(DateController.class);

    @Autowired
    private TimestampObjectService timestampObjectService;

    @RequestMapping(value = "/getSome", method = RequestMethod.GET)
    public TimestampObject index() {
        TimestampObject timestampObject = createTimestampObject();
        LOG.debug("index called.  Object={}", timestampObject);
        return timestampObject;
    }

    @RequestMapping(value = "/createSome", method = RequestMethod.POST)
    public void createSomeRandom(@RequestParam @NotNull Integer number) {
        for (int count = 0; count < number; count++) {
            timestampObjectService.create(createTimestampObject());
        }
    }

    @RequestMapping(value = "/getPersisted", method = RequestMethod.GET)
    public List<TimestampObject> getPersisted() {
        return timestampObjectService.findAll();
    }


    private TimestampObject createTimestampObject() {

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
