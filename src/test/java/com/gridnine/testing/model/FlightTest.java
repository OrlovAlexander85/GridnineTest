package com.gridnine.testing.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight flight;
    final LocalDateTime departure = LocalDateTime.of(2020, 1, 1, 0, 0);

    @BeforeEach
    void setUp() {
        flight = new Flight(Arrays.asList(
                new Segment(departure, departure.plusHours(2)),
                new Segment(departure.plusHours(3), departure.plusHours(5))
        ));
    }

    @Test
    void testMultiSegmentFlightDepartureIsDepartureOfFirstSegment() {
        LocalDateTime actualFirstDeparture = flight.getFlightDeparture();
        assertEquals(departure, actualFirstDeparture);
    }

    @Test
    void testMultiSegmentFlightArrivalIsArrivalOfLastSegment() {
        LocalDateTime actualLastArrival = flight.getFlightArrival();
        assertEquals(departure.plusHours(5), actualLastArrival);
    }

    @Test
    void testFlightDurationIsSumOfAllSegments() {
        assertEquals(flight.getFlightDuration(), Duration.ofHours(4));
    }

    @Test
    void testTotalDurationIsBetweenFirstDepartureAndLastArrival() {
        assertEquals(flight.getTotalDuration(), Duration.ofHours(5));
    }

}