package com.dev.flightsearch.model;

import com.dev.flightsearch.model.enums.FlightProviderType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "flight_provider_service_log")
public class FlightProviderServiceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type")
    private FlightProviderType providerType;

    @Column(columnDefinition = "jsonb")
    private String request;

    @Column(columnDefinition = "jsonb")
    private String response;

    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
