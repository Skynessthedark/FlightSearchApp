package com.dev.flightsearch.payload.soap;

import com.dev.flightsearch.adapter.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class FlightA implements Serializable {
    @XmlElement(name = "flightNo")
    private String flightNo;
    @XmlElement(name = "origin")
    private String origin;
    @XmlElement(name = "destination")
    private String destination;

    @XmlElement(name = "departuredatetime")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime departureDateTime;

    @XmlElement(name = "arrivaldatetime")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime arrivalDateTime;

    @XmlElement(name = "price")
    private BigDecimal price;

    public FlightA(String flightNo, String origin, String destination, LocalDateTime departureDateTime,
                   LocalDateTime arrivalDateTime, BigDecimal price) {
        this.flightNo = flightNo;
        this.origin = origin;
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.price = price;
    }

    public FlightA() {
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
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

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
