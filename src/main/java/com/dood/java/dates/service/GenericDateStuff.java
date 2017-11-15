package com.dood.java.dates.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.abs;

public class GenericDateStuff {

    static long getSecondsBetween(Instant first, Instant second) {
        return abs(ChronoUnit.SECONDS.between(first, second));
    }
}
