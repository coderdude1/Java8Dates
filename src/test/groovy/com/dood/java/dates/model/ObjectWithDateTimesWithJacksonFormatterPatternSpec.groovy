package com.dood.java.dates.model

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import static java.time.format.DateTimeFormatter.ofPattern

class ObjectWithDateTimesWithJacksonFormatterPatternSpec extends Specification {
    public static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy::HH:mm:ss.SSS")

    ObjectMapper objectMapper

    def setup() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)

    }

    def 'see what stuff looks like with JSR310 and a JsonFormat annotation'() {
        given:
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC"))
        LocalDateTime localDateTime = LocalDateTime.now()
        LocalDate localDate = LocalDate.now()
        Date date = new Date()
        ObjectWithDateTimesWithJacksonFormatterPattern dateObject = new ObjectWithDateTimesWithJacksonFormatterPattern()
                .setZonedDateTime(utcNow)
                .setLocalDateTime(localDateTime)
                .setLocalDate(localDate)
                .setUtilDate(date)

        when:
        def jsonInString = objectMapper.writeValueAsString(dateObject)
        println(jsonInString)

        then:
        jsonInString
    }

    def 'use a default formatter so we do not have to use jsonFormatter annotations' () {
        given:
        ObjectMapper mapper = new ObjectMapper()
        JavaTimeModule javaTimeModule=new JavaTimeModule()

        // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(FORMATTER ))
//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(FORMATTER))
        javaTimeModule.addSerializer(Instant.class, new LocalDateTimeSerializer(FORMATTER ))
        javaTimeModule.addDeserializer(Instant.class, new LocalDateTimeDeserializer(FORMATTER))
        mapper.registerModule(javaTimeModule)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        AuditEntry auditEntry = new AuditEntry()  //this class doesn't have the JsonFormatter annotations
        auditEntry.now()

        when:
        def jsonString = mapper.writeValueAsString(auditEntry)
        println(jsonString)

        then:
        jsonString
    }

}
