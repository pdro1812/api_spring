package com.project.api.dto;

public class UserResponse {
    private String message;
    private int user_count;

    public UserResponse(String message, int user_count) {
        this.message = message;
        this.user_count = user_count;
    }

    public String getMessage() {
        return message;
    }

    public int getUser_count() {
        return user_count;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }
}
