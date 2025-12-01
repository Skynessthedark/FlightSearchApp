package com.dev.flightsearch.controller;

import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.payload.FlightResponse;
import com.dev.flightsearch.service.FlightService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/all")
    public FlightResponse getAllFlights(@RequestBody FlightRequest request) {
        List<Flight> flights = flightService.getAllFlights(request);
        FlightResponse response = new FlightResponse();
        response.setFlights(flights);
        return response;
    }

    @PostMapping("/cheapest")
    public FlightResponse getCheapestFlights(@RequestBody FlightRequest request) {
        List<Flight> flights = flightService.getCheapestFlights(request);
        FlightResponse response = new FlightResponse();
        response.setFlights(flights);
        return response;
    }
}
