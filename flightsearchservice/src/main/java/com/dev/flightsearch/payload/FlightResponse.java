package com.dev.flightsearch.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse implements Serializable {
    private List<Flight> flights;
    private boolean hasError;
    private String errorMessage = Strings.EMPTY;
}
