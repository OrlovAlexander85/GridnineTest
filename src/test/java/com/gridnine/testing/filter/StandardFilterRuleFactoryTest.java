package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StandardFilterRuleFactoryTest {

    final LocalDateTime departure = LocalDateTime.of(2020, 1, 1, 0, 0);

    @Test
    void whenDepartureIsAfterCurrentMomentIS_DEPARTURE_AFTER_CURRENT_MOMENTIsTrue() {
        // GIVEN
        Flight flightWithDepartureAfterNow = new Flight(Arrays.asList(
                new Segment(LocalDateTime.now().plusDays(1), departure)
        ));

        // WHEN
        boolean predicateResult = StandardFilterRuleFactory.IS_DEPARTURE_AFTER_CURRENT_MOMENT.test(flightWithDepartureAfterNow);

        // THEN
        assertTrue(predicateResult);
    }

    @Test
    void whenDepartureIsBeforeCurrentMomentIS_DEPARTURE_AFTER_CURRENT_MOMENTIsFalse() {
        // GIVEN
        Flight flightWithDepartureBeforeNow = new Flight(Arrays.asList(
                new Segment(LocalDateTime.now().minusDays(1), departure)
        ));

        // WHEN
        boolean predicateResult = StandardFilterRuleFactory.IS_DEPARTURE_AFTER_CURRENT_MOMENT.test(flightWithDepartureBeforeNow);

        // THEN
        assertFalse(predicateResult);
    }


}