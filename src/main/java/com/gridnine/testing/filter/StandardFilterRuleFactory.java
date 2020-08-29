package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Predicate;

public class StandardFilterRuleFactory {
    public static final Predicate<Flight> IS_DEPARTURE_AFTER_CURRENT_MOMENT;
    public static final Predicate<Flight> IS_DEPARTURE_BEFORE_ARRIVAL_IN_SEGMENT;
    public static final Predicate<Flight> IS_GROUND_TIME_LESS_THAN_2_HOURS;

    static {
        IS_DEPARTURE_AFTER_CURRENT_MOMENT = f -> f.getFlightDeparture().isAfter(LocalDateTime.now());
        IS_DEPARTURE_BEFORE_ARRIVAL_IN_SEGMENT = f -> f.getSegments()
                .stream()
                .allMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())
                );
        IS_GROUND_TIME_LESS_THAN_2_HOURS = f -> f.getTotalDuration()
                .minus(f.getFlightDuration())
                .minus(Duration.ofHours(2))
                .isNegative();
    }

    private StandardFilterRuleFactory() {
    }
}
