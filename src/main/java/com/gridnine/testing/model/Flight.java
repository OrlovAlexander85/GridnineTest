package com.gridnine.testing.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean that represents a flight.
 */
public class Flight {
    private final List<Segment> segments;

    public Flight(final List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public LocalDateTime getFlightDeparture() {
        final int indexOfFirstSegment = 0;
        return segments.get(indexOfFirstSegment).getDepartureDate();
    }

    public LocalDateTime getFlightArrival() {
        final int indexOfLastSegment = segments.size() - 1;
        return segments.get(indexOfLastSegment).getArrivalDate();
    }

    public Duration getFlightDuration() {
        Duration totalFlightDuration = Duration.ZERO;
        for (Segment segment : segments) {
            totalFlightDuration = totalFlightDuration.plus(segment.getDuration());
        }
        return totalFlightDuration;
    }

    public Duration getTotalDuration() {
        return Duration.between(getFlightDeparture(), getFlightArrival());
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}