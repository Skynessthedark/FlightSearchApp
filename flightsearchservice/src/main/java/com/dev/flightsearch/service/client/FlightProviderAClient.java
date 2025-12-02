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
public class FlightProviderAClient {

    private final WebServiceTemplate webServiceTemplate;
    private final FlightProviderLogService flightProviderLogService;

    @Value("${soap.providerA.url}")
    private String providerAUrl;

    public FlightProviderAClient(WebServiceTemplate webServiceTemplate, FlightProviderLogService flightProviderLogService) {
        this.webServiceTemplate = webServiceTemplate;
        this.flightProviderLogService = flightProviderLogService;
    }

    public List<Flight> searchFlights(FlightRequest request) {
        FlightProviderARequest soapRequest = getSoapRequest(request);

        saveLog(soapRequest, null, false);

        FlightProviderAResponse response = null;
        try{
            response = (FlightProviderAResponse) webServiceTemplate.marshalSendAndReceive(providerAUrl, soapRequest);
            saveLog(soapRequest, response, Objects.isNull(response) || response.isHasError());
        }catch (Exception e){
            saveLog(soapRequest, null, true);
            e.printStackTrace();
        }

        return getFlightList(response);
    }

    private void saveLog(FlightProviderARequest request, FlightProviderAResponse response, boolean hasError){
        flightProviderLogService.saveLog(FlightProviderType.A, request, response, hasError);
    }

    private List<Flight> getFlightList(FlightProviderAResponse response) {
        if(Objects.isNull(response) || response.isHasError()) return List.of();

        return response.getFlightOptions().stream().map(f -> {
            Flight flight = new Flight();
            flight.setFlightNumber(f.getFlightNo());
            flight.setDeparture(f.getOrigin());
            flight.setArrival(f.getDestination());
            flight.setDepartureDate(f.getDepartureDateTime());
            flight.setArrivalDate(f.getArrivalDateTime());
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
