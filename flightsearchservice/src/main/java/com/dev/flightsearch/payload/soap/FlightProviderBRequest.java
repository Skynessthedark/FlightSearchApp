package com.dev.flightsearch.payload.soap;

import com.dev.flightsearch.adapter.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDateTime;

@XmlRootElement(name = "SearchRequest", namespace = "http://flightsearch.com/provider/b")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightProviderBRequest implements Serializable {

    @XmlElement(name = "departure", namespace = "http://flightsearch.com/provider/b")
    private String departure;
    @XmlElement(name = "arrival", namespace = "http://flightsearch.com/provider/b")
    private String arrival;

    @XmlElement(name = "departureDate", namespace = "http://flightsearch.com/provider/b")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime departureDate;

    public FlightProviderBRequest(String departure, String arrival, LocalDateTime departureDate) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
    }

    public FlightProviderBRequest() {
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }
}
