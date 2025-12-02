package com.dev.flightsearch.payload.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "SearchResult", namespace = "http://flightsearch.com/provider/b")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightProviderBResponse implements Serializable {

    @XmlElement(name = "hasError", namespace = "http://flightsearch.com/provider/b")
    private boolean hasError = false;

    @XmlElement(name = "flightOptions", namespace = "http://flightsearch.com/provider/b")
    private List<FlightB> flightOptions = new ArrayList<>();

    @XmlElement(name = "errorMessage", namespace = "http://flightsearch.com/provider/b")
    private String errorMessage;

    public FlightProviderBResponse() {
    }

    public FlightProviderBResponse(boolean hasError, List<FlightB> flightOptions, String errorMessage) {
        this.hasError = hasError;
        this.flightOptions = flightOptions;
        this.errorMessage = errorMessage;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public List<FlightB> getFlightOptions() {
        return flightOptions;
    }

    public void setFlightOptions(List<FlightB> flightOptions) {
        this.flightOptions = flightOptions;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
