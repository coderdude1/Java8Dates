package com.dood.java.dates.service;

import com.dood.java.dates.model.TimestampObject;
import com.dood.java.dates.repository.TimestampObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimestampObjectService {
    private static final Logger LOG = LoggerFactory.getLogger(TimestampObjectService.class);

    @Autowired
    private TimestampObjectRepository timestampObjectRepository;

    public void create(TimestampObject timestampObject) {
        timestampObjectRepository.save(timestampObject);
        LOG.info("persisted = {}", timestampObject);
    }

    public List<TimestampObject> findAll() {
        return timestampObjectRepository.findAll();
    }


    public void delete(TimestampObject timestampObject) {
        timestampObjectRepository.delete(timestampObject);
    }

    public void deleteAll() {
        timestampObjectRepository.deleteAll();
    }
}
