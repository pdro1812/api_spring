package com.project.api.dto;

public class TopCountryDTO {
    private String country;
    private long count;

    public TopCountryDTO(String country, long count){
        this.country = country;
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public long getCount() {
        return count;
    }

}
