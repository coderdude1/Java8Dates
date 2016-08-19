package com.dood.java.dates.repository;

import com.dood.java.dates.model.TimestampObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimestampObjectRepository  extends MongoRepository<TimestampObject, String> {
}
