package com.dev.flightsearch.service.client;

import com.dev.flightsearch.model.enums.FlightProviderType;
import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.payload.soap.FlightProviderBRequest;
import com.dev.flightsearch.payload.soap.FlightProviderBResponse;
import com.dev.flightsearch.service.FlightProviderLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightProviderBClient.class);

    @Value("${soap.providerB.url}")
    private String providerBUrl;

    public FlightProviderBClient(WebServiceTemplate webServiceTemplate, FlightProviderLogService flightProviderLogService) {
        this.webServiceTemplate = webServiceTemplate;
        this.flightProviderLogService = flightProviderLogService;
    }

    public List<Flight> searchFlights(FlightRequest request) {
        FlightProviderBRequest soapRequest = getSoapRequest(request);

        FlightProviderBResponse response = null;
        try{
            response = (FlightProviderBResponse) webServiceTemplate.marshalSendAndReceive(providerBUrl, soapRequest);
            saveLog(soapRequest, response, Objects.isNull(response) || response.isHasError());
        }catch (Exception e){
            saveLog(soapRequest, null, true);
            LOGGER.error("Error while searching flights from Provider B.", e);
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
        try{
            soapRequest.setDeparture(request.getDeparture());
            soapRequest.setArrival(request.getArrival());
            soapRequest.setDepartureDate(LocalDateTime.parse(request.getDepartureDate()));
            saveLog(soapRequest, null, false);
        }catch (Exception e){
            LOGGER.error("Error while creating soap request for Provider B.", e);
            saveLog(soapRequest, null, true);
        }

        return soapRequest;
    }
}
