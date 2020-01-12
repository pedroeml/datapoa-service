package com.service.datapoa.crud.pontotaxi.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PontoTaxiRequest {
    private String name;
    private String lat;
    private String lng;

    public PontoTaxiRequest() { }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        final String template = "PontoTaxiRequest[name='%s', lat='%s', lng='%s']";
        return String.format(template, this.name, this.lat, this.lng);
    }
}
