package com.dev.flightsearch.service.client;

import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.payload.soap.FlightProviderARequest;
import com.dev.flightsearch.payload.soap.FlightProviderAResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FlightProviderAClient {

    private final WebServiceTemplate webServiceTemplate;

    @Value("${soap.providerA.url}")
    private String providerAUrl;

    public FlightProviderAClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public List<Flight> searchFlights(FlightRequest request) {
        FlightProviderARequest soapRequest = getSoapRequest(request);

        //TODO: req log
        FlightProviderAResponse response =
                (FlightProviderAResponse) webServiceTemplate.marshalSendAndReceive(providerAUrl, soapRequest);

        //TODO: res log
        return getFlightList(response);
    }

    private List<Flight> getFlightList(FlightProviderAResponse response) {
        if(Objects.isNull(response) || response.isHasError()) return List.of();

        return response.getFlightOptions().stream().map(f -> {
            Flight flight = new Flight();
            flight.setFlightNumber(f.getFlightNo());
            flight.setDeparture(f.getOrigin());
            flight.setArrival(f.getDestination());
            flight.setDepartureDate(f.getDeparturedatetime());
            flight.setArrivalDate(f.getArrivaldatetime());
            flight.setPrice(f.getPrice());
            return flight;
        }).toList();
    }

    private FlightProviderARequest getSoapRequest(FlightRequest request) {
        FlightProviderARequest soapRequest = new FlightProviderARequest();
        soapRequest.setOrigin(request.getDeparture());
        soapRequest.setDestination(request.getArrival());
        soapRequest.setDepartureDate(LocalDateTime.parse(request.getDepartureDate()));
        return soapRequest;
    }
}
