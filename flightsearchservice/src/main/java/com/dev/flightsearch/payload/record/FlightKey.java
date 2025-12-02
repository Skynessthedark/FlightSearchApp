package com.dev.flightsearch.payload.record;

public record FlightKey(String flightNumber, String departure, String arrival,
                        String departureDate, String arrivalDate) {
}
