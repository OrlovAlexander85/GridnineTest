package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.function.Predicate;

public interface FlightFilter {

    List<Flight> filter(List<Flight> flights, Predicate<Flight> filterCondition);

}
