package com.dev.flightsearch.service.client;

import com.dev.flightsearch.model.enums.FlightProviderType;
import com.dev.flightsearch.payload.Flight;
import com.dev.flightsearch.payload.FlightRequest;
import com.dev.flightsearch.payload.soap.FlightProviderARequest;
import com.dev.flightsearch.payload.soap.FlightProviderAResponse;
import com.dev.flightsearch.service.FlightProviderLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class FlightProviderAClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightProviderAClient.class);

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

        FlightProviderAResponse response = null;
        try{
            response = (FlightProviderAResponse) webServiceTemplate.marshalSendAndReceive(providerAUrl, soapRequest);
            saveLog(soapRequest, response, Objects.isNull(response) || response.isHasError());
        }catch (Exception e){
            saveLog(soapRequest, null, true);
            LOGGER.error("Error while searching flights from Provider A.", e);
        }

        return getFlightList(response);
    }

    private void saveLog(FlightProviderARequest request, FlightProviderAResponse response, boolean hasError){
        flightProviderLogService.saveLog(FlightProviderType.A, request, response, hasError);
    }

    private List<Flight> getFlightList(FlightProviderAResponse response) {
        if(Objects.isNull(response) || response.isHasError()
                || CollectionUtils.isEmpty(response.getFlightOptions())) return List.of();

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
        try{
            soapRequest.setOrigin(request.getDeparture());
            soapRequest.setDestination(request.getArrival());
            soapRequest.setDepartureDate(LocalDateTime.parse(request.getDepartureDate()));
            saveLog(soapRequest, null, false);
        }catch (Exception e){
            LOGGER.error("Error while creating soap request for Provider A.", e);
            saveLog(soapRequest, null, true);
        }
        return soapRequest;
    }
}
