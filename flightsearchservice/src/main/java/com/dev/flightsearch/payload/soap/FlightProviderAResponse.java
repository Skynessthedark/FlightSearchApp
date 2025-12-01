package com.dev.flightsearch.payload.soap;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FlightProviderAResponse implements Serializable {
    private List<FlightA> flightOptions;
    private boolean hasError;
    private String errorMessage;
}
