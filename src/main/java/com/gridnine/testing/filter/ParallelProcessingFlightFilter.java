package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ParallelProcessingFlightFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights, Predicate<Flight> filterCondition) {
        if (flights == null || filterCondition == null) {
            return Collections.emptyList();
        }
        return flights.parallelStream().filter(filterCondition).collect(Collectors.toList());
    }
}
