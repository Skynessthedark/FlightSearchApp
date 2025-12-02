package com.flightservice.providerb.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "SearchResult", namespace = "http://flightsearch.com/provider/b")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResult {
	@XmlElement(name = "hasError", namespace = "http://flightsearch.com/provider/b")
	private boolean hasError = false;

	@XmlElement(name = "flightOptions", namespace = "http://flightsearch.com/provider/b")
	private List<Flight> flightOptions = new ArrayList<>();

	@XmlElement(name = "errorMessage", namespace = "http://flightsearch.com/provider/b")
	private String errorMessage;

	public SearchResult() {
	}

	public SearchResult(boolean hasError, List<Flight> flightOptions, String errorMessage) {
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

	public List<Flight> getFlightOptions() {
		return flightOptions;
	}

	public void setFlightOptions(List<Flight> flightOptions) {
		this.flightOptions = flightOptions;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}