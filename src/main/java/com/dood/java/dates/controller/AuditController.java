package com.dood.java.dates.controller;

import com.dood.java.dates.model.AuditEntry;
import com.dood.java.dates.service.AuditEntryService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/audits")
@RestController
public class AuditController {
    private static final Logger LOG = LoggerFactory.getLogger(AuditController.class);

    @Autowired
    private AuditEntryService auditEntryService;

    @RequestMapping(value = "/getOneNow", method = RequestMethod.GET)
    public AuditEntry getOneNow() {
        return auditEntryService.createOneNow();
    }

    /**
     * Add not null validation
     *
     * @param count
     */
    @RequestMapping(value = "/createSome", method = RequestMethod.POST)
    public void persistSome(@RequestParam Integer count) {
        auditEntryService.createRandomAuditEntries(count);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<AuditEntry> findAll() {
        return auditEntryService.findAll();
    }


    @RequestMapping(value="/createNew", method = RequestMethod.POST)
    public AuditEntry createNew(@RequestBody @ApiParam(value = "auditEntry.id", hidden = true) AuditEntry auditEntry) {
        auditEntryService.create(auditEntry);
        return auditEntry;
    }
}

