package com.project.api.Model;

public class TopCountryDTO {
    private String country;
    private long user_count;

    public TopCountryDTO(String country, long user_count) {
        this.country = country;
        this.user_count = user_count;
    }

    // Getters and Setters
    public String getCountry() {
        return country;
    }

    public long getUser_count() {
        return user_count;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUser_count(long user_count) {
        this.user_count = user_count;
    }
}
