package com.dev.flightsearch.payload.soap;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FlightProviderBRequest implements Serializable {
    private String departure;
    private String arrival;
    private LocalDateTime departureDate;
}
