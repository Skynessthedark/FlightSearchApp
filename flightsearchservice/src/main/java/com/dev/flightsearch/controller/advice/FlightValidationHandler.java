package com.dev.flightsearch.controller.advice;

import com.dev.flightsearch.exception.InvalidFlightDateException;
import com.dev.flightsearch.payload.FlightResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class FlightValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FlightResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.ok(new FlightResponse(List.of(), true, String.join(", ", errors)));
    }

    @ExceptionHandler(InvalidFlightDateException.class)
    public ResponseEntity<FlightResponse> handleInvalidFlightDateException(InvalidFlightDateException ex) {
        return ResponseEntity.ok(new FlightResponse(List.of(), true, ex.getMessage()));
    }
}
