package com.dev.flightsearch.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class FlightRequest implements Serializable {

    @NotBlank(message = "Departure cannot be empty.")
    private String departure;
    @NotBlank(message = "Arrival cannot be empty.")
    private String arrival;

    @NotBlank(message = "Departure date cannot be empty.")
    private String departureDate;
}
