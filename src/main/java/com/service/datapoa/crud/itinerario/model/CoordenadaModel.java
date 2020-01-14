package com.service.datapoa.crud.itinerario.model;

public class CoordenadaModel {
    private final String lat;
    private final String lng;

    public CoordenadaModel(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @Override
    public String toString() {
        final String template = "CoordenadaModel[lat='%s', lng='%s']";
        return String.format(template, this.lat, this.lng);
    }
}
