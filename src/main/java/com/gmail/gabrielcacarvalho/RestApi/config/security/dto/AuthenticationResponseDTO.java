package com.gmail.gabrielcacarvalho.RestApi.config.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;

public class AuthenticationResponseDTO {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private Long expiresIn;
    @JsonProperty("issued")
    private Calendar issued;
    @JsonProperty("expires")
    private Calendar expires;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Calendar getIssued() {
        return issued;
    }

    public void setIssued(Calendar issued) {
        this.issued = issued;
    }

    public void setExpires(Calendar expires) {
        this.expires = expires;
    }

    public void setIssued(Date issued) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issued);
        this.issued = calendar;
    }

    public Calendar getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(expires);
        this.expires = calendar;
    }
}
