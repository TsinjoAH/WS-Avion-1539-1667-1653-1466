package com.example.gestionflotte.responses;

public class AuthenticatedDetails {
    private String token;

    public AuthenticatedDetails(String token) {
        this.token = token;
    }

    public AuthenticatedDetails() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
