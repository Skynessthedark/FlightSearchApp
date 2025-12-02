package com.dev.flightsearch.controller;

import com.dev.flightsearch.exception.InvalidFlightDateException;
import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.payload.FlightResponse;
import com.dev.flightsearch.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/all")
    public FlightResponse getAllFlights(@Valid @RequestBody FlightRequest request){
        if(isDepartureDateInvalid(request))
            throw new InvalidFlightDateException("Departure date cannot be in the past.");

        List<Flight> flights = flightService.getAllFlights(request);
        FlightResponse response = new FlightResponse();
        response.setFlights(flights);
        return response;
    }

    @PostMapping("/cheapest")
    public FlightResponse getCheapestFlights(@Valid @RequestBody FlightRequest request){
        if(isDepartureDateInvalid(request))
            throw new InvalidFlightDateException("Departure date cannot be in the past.");

        List<Flight> flights = flightService.getCheapestFlights(request);
        FlightResponse response = new FlightResponse();
        response.setFlights(flights);
        return response;
    }

    private boolean isDepartureDateInvalid(FlightRequest request){
        LocalDateTime date = LocalDateTime.parse(request.getDepartureDate(), DateTimeFormatter.ISO_DATE_TIME);
        return date.isBefore(LocalDateTime.now());
    }
}
