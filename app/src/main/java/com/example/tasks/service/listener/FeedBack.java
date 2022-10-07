package com.example.tasks.service.listener;

public class FeedBack {
    private Boolean success = true;
    private String message = "";

    public FeedBack() {
    }

    public FeedBack(String message) {
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
