package com.example.tasks.service.listener;

public interface APIListeners<T> {
    void onSuccess(T result);
    void onFailure(String message);
}
