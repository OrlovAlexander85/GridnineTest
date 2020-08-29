package com.gridnine.testing;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.ParallelProcessingFlightFilter;
import com.gridnine.testing.filter.StandardFilterRuleFactory;
import com.gridnine.testing.model.Flight;

import java.util.List;

public class Main {

    private static FlightFilter flightFilter;

    static {
        flightFilter = new ParallelProcessingFlightFilter();
    }

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("\nTest 1: исключить вылеты до текущего момента времени");
        List<Flight> filteredFlights = flightFilter.filter(flights, StandardFilterRuleFactory.IS_DEPARTURE_AFTER_CURRENT_MOMENT);
        System.out.println(filteredFlights);

        System.out.println("\nTest 2: исключить  рейсы, где имеются сегменты с датой прилёта раньше даты вылета");
        filteredFlights = flightFilter.filter(flights, StandardFilterRuleFactory.IS_DEPARTURE_BEFORE_ARRIVAL_IN_SEGMENT);
        System.out.println(filteredFlights);

        System.out.println("\nTest 3: исключить рейсы, где общее время, проведённое на земле превышает два часа (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)");
        filteredFlights = flightFilter.filter(flights, StandardFilterRuleFactory.IS_GROUND_TIME_LESS_THAN_2_HOURS);
        System.out.println(filteredFlights);

    }
}
