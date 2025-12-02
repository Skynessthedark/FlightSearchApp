package com.dev.flightsearch.service;

import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.payload.record.FlightKey;
import com.dev.flightsearch.service.client.FlightProviderAClient;
import com.dev.flightsearch.service.client.FlightProviderBClient;
import org.springframework.stereotype.Service;

import java.util.*;
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

        Map<FlightKey, List<Flight>> groupedFlights = flights.stream().collect(
                Collectors.groupingBy(flight ->
                        new FlightKey(flight.getFlightNumber(), flight.getDeparture(), flight.getArrival(),
                            flight.getDepartureDate().toString(),flight.getArrivalDate().toString())));

        List<Flight> cheapestFlights = new ArrayList<>(groupedFlights.values().stream().map(f ->
                f.stream().min(Comparator.comparing(Flight::getPrice)).get()).toList());

        cheapestFlights.sort(Comparator.comparing(Flight::getPrice));
        return cheapestFlights;
    }
}
