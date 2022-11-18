package com.example.outfit7.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Column(name = "time_zone")
    private String timeZone;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "api_calls")
    private int apiCallsCount;

    public User() {
        this.apiCallsCount = 1;
    }

    public int getApiCallsCount() {
        return this.apiCallsCount;
    }

    public void setApiCallsCount(int apiCallsCount) {
        this.apiCallsCount = apiCallsCount;
    }

    public User(String timeZone, String userId, String countryCode) {
        this.timeZone = timeZone;
        this.userId = userId;
        this.countryCode = countryCode;
        this.apiCallsCount = 1;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
