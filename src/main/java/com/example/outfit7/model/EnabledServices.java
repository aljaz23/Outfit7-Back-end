package com.example.outfit7.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnabledServices {

    private String multiplayer;
    private String userSupport;
    private String ads;

    public EnabledServices(boolean customerSupportEnabled, int apiCallsCount, boolean partnerSupportsDevice) {
        this.userSupport = customerSupportEnabled ? "enabled" : "disabled";
        this.ads = partnerSupportsDevice ? "enabled" : "disabled";
        this.multiplayer = (apiCallsCount > 5) ? "enabled" : "disabled";
    }

    @JsonProperty("user-support")
    public String getUserSupport() {
        return userSupport;
    }

    public String getMultiplayer() {
        return multiplayer;
    }

    public String getAds() {
        return ads;
    }

    @Override
    public String toString() {
        return userSupport + ads + multiplayer;
    }

}
