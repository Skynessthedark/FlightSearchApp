package com.dev.flightsearch.payload.soap;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightB implements Serializable {
    private String flightNumber;
    private String departure;
    private String arrival;
    private LocalDateTime departuredatetime;
    private LocalDateTime arrivaldatetime;
    private BigDecimal price;
}
