package com.dev.flightsearch.repository;

import com.dev.flightsearch.model.FlightProviderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightProviderLogRepository extends JpaRepository<FlightProviderLog, Long> {
}
