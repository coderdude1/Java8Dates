package com.dood.java.dates.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

//@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        boolean enableFeature = true; //set a breakpoint and change the value if you want
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, enableFeature);
//        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, enableFeature);
        return objectMapper;
    }

}
