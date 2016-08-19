package com.dood.java.dates;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Java8DatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Java8DatesApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error")))//exclude spring error controllers// for now
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Java Dates Rest API",
                "Various ways to exercise the Java8 dates, serialize/deserialize, mongo, etc",
                "1.0",
                "TOS",
                new Contact("coderdude1", "https://github.com/coderdude1/Java8Dates", "email"),
                "License: APL 2.0",
                "License URL: https://github.com/coderdude1/Java8Dates/blob/develop/License.md");
    }
}
