package com.dood.java.dates.service

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.Instant

class GenericDateStuffSpec extends Specification {

    GenericDateStuff subject = new GenericDateStuff()

    @Shared
    Instant now = Instant.now()

    @Unroll
    def 'showSeconds Between'() {
        when:
        long diffInSeconds = GenericDateStuff.getSecondsBetween(now, otherDate)

        then:
        diffInSeconds == 12

        where:
        otherDate                            || expectedDiff
        now.minusSeconds(12) || 12
        now.plusSeconds(12)     || 12
    }

}
