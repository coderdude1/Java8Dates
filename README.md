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

## New java8 date types
You can look up the details behind Oracle releasing new date types for java elsewhere, but
three major ones are:
1. Thread Safety
2. Mutability
3. 

## MongoDB
### Mongo Config
You will currently need to have a local mongo db running on the default port (unless you modify the application.yml).

### SpringDataMongo  
Note that ZonedDateTime is not supported by SpringData  https://jira.spring.io/browse/DATACMNS-834?jql=text%20~%20%22ZonedDateTime%22
as mongo currently doesn't support it (there is a jira issue for mongo to support JSR310 objects).  An
interesting observation in an early version of this code is that I persisted a zoned date time without
errors, but when I did a findAll() it blew up.

Document in mongo:

    {
         "_id" : ObjectId("57b66b08d985b73420332478"),
         "_class" : "com.dood.java.dates.model.TimestampObject",
         "localDate" : ISODate("2016-08-18T05:00:00Z"),
         "localDateTime" : ISODate("2016-08-19T02:12:24.867Z"),
         "zonedDateTime" : {
             "dateTime" : ISODate("2016-08-19T02:12:24.867Z"),
             "offset" : "-05:00",
             "zone" : "America/Chicago"
         }
    }
       
Another interesting observation is that the localDate and LocalDateTIme objects is stored at zulu time (the above
timestamps was created on that date, and we were GMT-5, thus 9:12:24 local time which is correct).  The
localDate apparently stored the time as midnight (matches the spring data mongo docs for this type).


## Jackson Serialization
### Swagger
There is a controller that implements swagger, go to http://localhost:8080 and it will redirect to the
swagger endpoint, allowing you to perform various operations.  You can play around with the swagger
as documented below, restart the container (unless I find a way to progamattically change these
configs)

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