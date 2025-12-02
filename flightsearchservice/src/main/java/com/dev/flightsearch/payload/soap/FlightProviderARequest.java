package com.dev.flightsearch.payload.soap;

import com.dev.flightsearch.adapter.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDateTime;

@XmlRootElement(name = "SearchRequest", namespace = "http://flightsearch.com/provider/a")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightProviderARequest implements Serializable {
    @XmlElement(name = "origin", namespace = "http://flightsearch.com/provider/a")
    private String origin = "";
    @XmlElement(name = "destination", namespace = "http://flightsearch.com/provider/a")
    private String destination = "";

    @XmlElement(name = "departureDate", namespace = "http://flightsearch.com/provider/a")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime departureDate;

    public FlightProviderARequest() {
    }

    public FlightProviderARequest(String origin, String destination, LocalDateTime departureDate) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }
}
