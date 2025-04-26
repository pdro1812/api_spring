package com.project.api.dto;

import java.util.List;

public class SuperUserResponseDTO {
    private String timestamp;
    private long execution_time_ms;
    private List<String> data;

    public SuperUserResponseDTO(String timestamp, long execution_time_ms, List<String> data) {
        this.timestamp = timestamp;
        this.execution_time_ms = execution_time_ms;
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public long getExecution_time_ms() {
        return execution_time_ms;
    }

    public List<String> getData() {
        return data;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setExecution_time_ms(long execution_time_ms) {
        this.execution_time_ms = execution_time_ms;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
