package com.example.tasks.service.listener;

public class Feedback {
    private Boolean success = true;
    private String message = "";

    public Feedback() {
    }

    public Feedback(String message) {
        this.message = message;
        this.success = false;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
