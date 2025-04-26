package com.project.api.dto;

import java.util.List;

public class TopCountriesResponseDTO {
    private String timestamp;
    private long execution_time_ms;
    private List<TopCountryDTO> countries;

    public TopCountriesResponseDTO(String timestamp, long execution_time_ms, List<TopCountryDTO> countries) {
        this.timestamp = timestamp;
        this.execution_time_ms = execution_time_ms;
        this.countries = countries;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getExecution_time_ms() {
        return execution_time_ms;
    }

    public List<TopCountryDTO> getCountries() {
        return countries;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setExecution_time_ms(long execution_time_ms) {
        this.execution_time_ms = execution_time_ms;
    }

    public void setCountries(List<TopCountryDTO> countries) {
        this.countries = countries;
    }
}
