package com.dev.flightsearch.payload.soap;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FlightProviderARequest implements Serializable {
    private String origin;
    private String destination;
    private LocalDateTime departureDate;
}
