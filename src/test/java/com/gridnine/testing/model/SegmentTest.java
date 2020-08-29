package com.gridnine.testing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SegmentTest {

    private Segment segment;

    @Test
    void whenArrival2HoursAfterDepartureThenDurationIs2Hours() {
        LocalDateTime departure = LocalDateTime.of(2020, 3, 3, 0, 0);
        segment = new Segment(departure, departure.plusHours(2));
        assertEquals(Duration.ofHours(2), segment.getDuration());
    }

    @Test
    void whenNullsArePassedThrowsException() {
        assertThrows(NullPointerException.class, () -> new Segment(null, null));
    }

}