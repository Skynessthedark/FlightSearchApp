package com.dev.flightsearch.service.client;

import com.dev.flightsearch.model.enums.FlightProviderType;
import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.payload.soap.FlightProviderARequest;
import com.dev.flightsearch.payload.soap.FlightProviderAResponse;
import com.dev.flightsearch.payload.soap.FlightProviderBRequest;
import com.dev.flightsearch.payload.soap.FlightProviderBResponse;
import com.dev.flightsearch.service.FlightProviderLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FlightProviderBClient {

    private final WebServiceTemplate webServiceTemplate;
    private final FlightProviderLogService flightProviderLogService;

    @Value("${soap.providerB.url}")
    private String providerBUrl;

    public FlightProviderBClient(WebServiceTemplate webServiceTemplate, FlightProviderLogService flightProviderLogService) {
        this.webServiceTemplate = webServiceTemplate;
        this.flightProviderLogService = flightProviderLogService;
    }

    public List<Flight> searchFlights(FlightRequest request) {
        FlightProviderBRequest soapRequest = getSoapRequest(request);

        saveLog(soapRequest, null, false);

        FlightProviderBResponse response = null;
        try{
            response = (FlightProviderBResponse) webServiceTemplate.marshalSendAndReceive(providerBUrl, soapRequest);
            saveLog(soapRequest, response, Objects.isNull(response) || response.isHasError());
        }catch (Exception e){
            saveLog(soapRequest, null, true);
            e.printStackTrace();
        }

        return getFlightList(response);
    }

    private void saveLog(FlightProviderBRequest request, FlightProviderBResponse response, boolean hasError){
        flightProviderLogService.saveLog(FlightProviderType.B, request, response, hasError);
    }

    private List<Flight> getFlightList(FlightProviderBResponse response) {
        if(Objects.isNull(response) || response.isHasError()) return List.of();

        return response.getFlightOptions().stream().map(f -> {
            Flight flight = new Flight();
            flight.setFlightNumber(f.getFlightNumber());
            flight.setDeparture(f.getDeparture());
            flight.setArrival(f.getArrival());
            flight.setDepartureDate(f.getDepartureDateTime());
            flight.setArrivalDate(f.getArrivalDateTime());
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
