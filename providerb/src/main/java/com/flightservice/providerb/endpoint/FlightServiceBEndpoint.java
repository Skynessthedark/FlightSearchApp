package com.flightservice.providerb.endpoint;

import com.flightservice.providerb.model.SearchRequest;
import com.flightservice.providerb.model.SearchResult;
import com.flightservice.providerb.service.SearchService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FlightServiceBEndpoint {

    private static final String NAMESPACE_URI = "http://flightsearch.com/provider/b";

    private final SearchService searchService;

    public FlightServiceBEndpoint(SearchService searchService) {
        this.searchService = searchService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SearchRequest")
    @ResponsePayload
    public SearchResult searchFlights(@RequestPayload SearchRequest request){
        return searchService.availabilitySearch(request);
    }
}
