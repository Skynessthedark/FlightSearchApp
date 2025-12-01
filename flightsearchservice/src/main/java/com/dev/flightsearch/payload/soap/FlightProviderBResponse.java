package com.dev.flightsearch.payload.soap;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class FlightProviderBResponse implements Serializable {
    private List<FlightB> flightOptions = new ArrayList<>();
    private boolean hasError;
    private String errorMessage;
}
