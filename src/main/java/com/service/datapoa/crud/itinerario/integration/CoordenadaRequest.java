package com.service.datapoa.crud.itinerario.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoordenadaRequest {
    private String lat;
    private String lng;

    public CoordenadaRequest() { }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        final String template = "CoordenadaRequest[lat='%s', lng='%s']";
        return String.format(template, this.lat, this.lng);
    }
}
