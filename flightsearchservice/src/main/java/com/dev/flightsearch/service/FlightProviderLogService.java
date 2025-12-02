package com.dev.flightsearch.service;

import com.dev.flightsearch.model.FlightProviderLog;
import com.dev.flightsearch.model.enums.FlightProviderType;
import com.dev.flightsearch.repository.FlightProviderLogRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Objects;

@Service
public class FlightProviderLogService {

    private final FlightProviderLogRepository flightProviderLogRepository;

    public FlightProviderLogService(FlightProviderLogRepository flightProviderLogRepository) {
        this.flightProviderLogRepository = flightProviderLogRepository;
    }

    public void saveLog(FlightProviderType type, Object request, Object response, boolean hasError){
        FlightProviderLog log = new FlightProviderLog();
        log.setProviderType(type);
        log.setRequest(convertToString(request));
        log.setResponse(convertToString(response));
        log.setHasError(hasError);
        flightProviderLogRepository.save(log);
    }

    private String convertToString(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        return Objects.nonNull(obj)? objectMapper.writeValueAsString(obj): Strings.EMPTY;
    }
}
