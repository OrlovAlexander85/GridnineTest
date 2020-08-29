package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParallelProcessingFlightFilterTest {

    private FlightFilter flightFilter;
    private Flight flight;
    final LocalDateTime departure = LocalDateTime.of(2020, 1, 1, 0, 0);

    @BeforeEach
    void setUp() {
        flightFilter = new ParallelProcessingFlightFilter();
        flight = new Flight(Arrays.asList(
                new Segment(departure, departure.plusHours(2)),
                new Segment(departure.plusHours(3), departure.plusHours(5))
        ));
    }

    @Test
    void whenFlightListOrFilterConditionIsNullThenReturnEmptyCollection() {
        List<Flight> flights = flightFilter.filter(null, null);
        assertNotNull(flights);
        assertEquals(0, flights.size());
    }

    @Test
    void whenAllowAllThenReturnACopy() {
        // GIVEN
        List<Flight> inputFlights = Arrays.asList(flight, flight, flight);
        Predicate<Flight> allowAll = v -> true;

        // WHEN
        List<Flight> filteredFlights = flightFilter.filter(inputFlights, allowAll);

        // THEN
        Flight[] actualFilteredFlightsArray = new Flight[filteredFlights.size()];
        filteredFlights.toArray(actualFilteredFlightsArray);

        Flight[] inputFlightsArray = new Flight[inputFlights.size()];
        inputFlights.toArray(inputFlightsArray);

        assertArrayEquals(inputFlightsArray, actualFilteredFlightsArray);
    }

    @Test
    void whenBlockAllThenReturnEmptyCollection() {
        // GIVEN
        List<Flight> inputFlights = Arrays.asList(flight, flight, flight);
        Predicate<Flight> blockAll = v -> false;

        // WHEN
        List<Flight> filteredFlights = flightFilter.filter(inputFlights, blockAll);

        // THEN
        assertNotNull(filteredFlights);
        assertEquals(0, filteredFlights.size());
    }
}