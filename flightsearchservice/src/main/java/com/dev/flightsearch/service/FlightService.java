package com.dev.flightsearch.service;

import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.service.client.FlightProviderAClient;
import com.dev.flightsearch.service.client.FlightProviderBClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightProviderAClient flightProviderAClient;
    private final FlightProviderBClient flightProviderBClient;

    public FlightService(FlightProviderAClient flightProviderAClient, FlightProviderBClient flightProviderBClient) {
        this.flightProviderAClient = flightProviderAClient;
        this.flightProviderBClient = flightProviderBClient;
    }

    public List<Flight> getAllFlights(FlightRequest request) {
        List<Flight> flights = new ArrayList<>();
        flights.addAll(flightProviderAClient.searchFlights(request));
        flights.addAll(flightProviderBClient.searchFlights(request));
        return flights;
    }

    public List<Flight> getCheapestFlights(FlightRequest request) {
        List<Flight> flights = getAllFlights(request);
        return flights.stream()
                .collect(Collectors.groupingBy(f -> f.getFlightNumber() + "|" +
                        f.getDeparture() + "|" +
                        f.getArrival() + "|" +
                        f.getDepartureDate() + "|" +
                        f.getArrivalDate()))
                .values()
                .stream()
                .map(fs -> fs.stream()
                        .min(Comparator.comparing(Flight::getPrice))
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
