package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SequentialProcessingFlightFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights, Predicate<Flight> filterCondition) {
        return flights.stream().filter(filterCondition).collect(Collectors.toList());
    }
}
