package com.dev.flightsearch.payload;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Flight implements Serializable {
    private String flightNumber;
    private String departure;
    private String arrival;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private BigDecimal price;
}
