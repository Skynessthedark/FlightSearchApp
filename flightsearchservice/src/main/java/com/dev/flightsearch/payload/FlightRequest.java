package com.dev.flightsearch.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class FlightRequest implements Serializable {
    private String departure;
    private String arrival;
    private String departureDate;
}
