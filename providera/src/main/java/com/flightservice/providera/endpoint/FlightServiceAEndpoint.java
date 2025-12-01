package com.flightservice.providera.endpoint;

import com.flightservice.providera.model.SearchRequest;
import com.flightservice.providera.model.SearchResult;
import com.flightservice.providera.service.SearchService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FlightServiceAEndpoint {

    private static final String NAMESPACE_URI = "http://flightsearch.com/provider/a";

    private final SearchService searchService;

    public FlightServiceAEndpoint(SearchService searchService) {
        this.searchService = searchService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SearchRequest")
    @ResponsePayload
    public SearchResult searchFlights(@RequestPayload SearchRequest request){
        return searchService.availabilitySearch(request);
    }
}
