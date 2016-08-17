# Experiments with Java8 dates
## Overview
Several experiments using java 8 time objects.  Focus intially is on serialization with jackson
and persistence using mongo (springdata and non-spring data).  Eventually I'll add some of the
nice joda-time utility methods and classes

## General Notes
This is a springboot class, using intellij you can just import from existing sources and should be
able to run the main springboot application class.

## Stuff todo still
1. add some real unit tests experiment with config options
2. add swagger, more endpoints to get, put, and post data
3. Can I configure jackson with annotations and enums?  I've seen some stuff

## Jackson Serializatoin
### Default spring boot/jackson 
This one is a partial clip of the output, as the ZonedDateTime has hundreds of fields that get serialized

    localDate: {
            year: 2016,
            month: "AUGUST",
            chronology: {
                id: "ISO",
                calendarType: "iso8601"
            },
            era: "CE",
            dayOfMonth: 16,
            dayOfWeek: "TUESDAY",
            dayOfYear: 229,
            leapYear: true,
            monthValue: 8
        },
        localDateTime: {
            hour: 21,
            minute: 10,
            nano: 703000000,
            second: 25,
            dayOfMonth: 16,
            dayOfWeek: "TUESDAY",
            dayOfYear: 229,
            month: "AUGUST",
            monthValue: 8,
            year: 2016,
            chronology: {
                id: "ISO",
                calendarType: "iso8601"
            }
        },
        zonedDateTime: {
            offset: {
                totalSeconds: -18000,
                id: "-05:00",
                    rules: {
                    fixedOffset: true,
                    transitionRules: [ ],
                    transitions: [ ]
            }
        },
        zone: {
            id: "America/Chicago",
            rules: {
            fixedOffset: false,
            transitionRules: [
            {
                month: "MARCH",
                timeDefinition: "WALL",
                standardOffset: {
                totalSeconds: -21600,
                id: "-06:00",
                    rules: {
                        fixedOffset: true,
                        transitionRules: [ ],
                        transitions: [ ]
        }
    },

### Adding the Jackson JSR310 support lib to the classpath, no configuration, ie default output

    {
        localDate: [
            2016,
            8,
            16
        ],
        localDateTime: [
            2016,
            8,
            16,
            21,
            0,
            29,
            94000000
        ],
        zonedDateTime: 1471399229.094
    }

###Using the JacksonConfig along with the JSR310 lib to control the jackson engine.
#### Using the WRITE_DATES_AS_TIMESTAMPS

Value set to false

    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    {
        localDate: "2016-08-16",
        localDateTime: "2016-08-16T20:39:58.611",
        zonedDateTime: "2016-08-16T20:39:58.611-05:00"
    }
    
Value set to true

    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
    
    {
        localDate: [
            2016,
            8,
            16
        ],
        localDateTime: [
            2016,
            8,
            16,
            20,
            53,
            34,
            639000000
        ],
        zonedDateTime: 1471398814.639
    }
    
####Using the WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS.  
note this is the same output as if we did not use the JacksonCOnfig, but juust
added the Jackson support for JSR310 lib to the classpath

Value set to false

    objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
    {
        localDate: [
            2016,
            8,
            16
        ],
        localDateTime: [
            2016,
            8,
            16,
            20,
            47,
            2,
            564
        ],
        zonedDateTime: 1471398422564
    }
    
Value set to true.  This outputs the same as if we did not add the JacksonConfig, and just added
the Jackson support for JSR310 lib to the classpath (ie note the nanonseconds for the ZonedDateTime
    
    objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
    
    {
        localDate: [
            2016,
            8,
            16
        ],
        localDateTime: [
            2016,
            8,
            16,
            20,
            49,
            34,
            732000000
        ],
        zonedDateTime: 1471398574.732
    }