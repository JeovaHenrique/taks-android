package com.example.tasks.service.model;

import com.google.gson.annotations.SerializedName;

public class PersonModel {

    @SerializedName("token")
    private String token;

    @SerializedName("personKey")
    private String personKey;

    @SerializedName("name")
    private String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPersonKey() {
        return personKey;
    }

    public void setPersonKey(String personKey) {
        this.personKey = personKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
