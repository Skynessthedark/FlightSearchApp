package com.dev.flightsearch.service.client;

import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.payload.soap.FlightProviderARequest;
import com.dev.flightsearch.payload.soap.FlightProviderAResponse;
import com.dev.flightsearch.payload.soap.FlightProviderBRequest;
import com.dev.flightsearch.payload.soap.FlightProviderBResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FlightProviderBClient {

    private final WebServiceTemplate webServiceTemplate;

    @Value("${soap.providerB.url}")
    private String providerBUrl;

    public FlightProviderBClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public List<Flight> searchFlights(FlightRequest request) {
        FlightProviderBRequest soapRequest = getSoapRequest(request);

        //TODO: req log
        FlightProviderBResponse response =
                (FlightProviderBResponse) webServiceTemplate.marshalSendAndReceive(providerBUrl, soapRequest);

        //TODO: res log

        return getFlightList(response);
    }

    private List<Flight> getFlightList(FlightProviderBResponse response) {
        if(Objects.isNull(response) || response.isHasError()) return List.of();

        return response.getFlightOptions().stream().map(f -> {
            Flight flight = new Flight();
            flight.setFlightNumber(f.getFlightNumber());
            flight.setDeparture(f.getDeparture());
            flight.setArrival(f.getArrival());
            flight.setDepartureDate(f.getDeparturedatetime());
            flight.setArrivalDate(f.getArrivaldatetime());
            flight.setPrice(f.getPrice());
            return flight;
        }).toList();
    }

    private FlightProviderBRequest getSoapRequest(FlightRequest request) {
        FlightProviderBRequest soapRequest = new FlightProviderBRequest();
        soapRequest.setDeparture(request.getDeparture());
        soapRequest.setArrival(request.getArrival());
        soapRequest.setDepartureDate(LocalDateTime.parse(request.getDepartureDate()));
        return soapRequest;
    }
}
