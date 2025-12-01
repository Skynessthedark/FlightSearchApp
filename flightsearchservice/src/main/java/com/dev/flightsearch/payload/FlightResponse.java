package com.dev.flightsearch.payload;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FlightResponse implements Serializable {
    private List<Flight> flights;
}
