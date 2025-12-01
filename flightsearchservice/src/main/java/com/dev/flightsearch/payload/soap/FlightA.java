package com.dev.flightsearch.payload.soap;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FlightA implements Serializable {
    private String flightNo;
    private String origin;
    private String destination;
    private LocalDateTime departuredatetime;
    private LocalDateTime arrivaldatetime;
    private BigDecimal price;
}
