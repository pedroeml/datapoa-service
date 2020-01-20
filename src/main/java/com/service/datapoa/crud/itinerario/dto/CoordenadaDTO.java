package com.service.datapoa.crud.itinerario.dto;

import com.service.datapoa.crud.itinerario.model.CoordenadaModel;

public class CoordenadaDTO {
    private final String lat;
    private final String lng;

    public CoordenadaDTO(CoordenadaModel model) {
        this.lat = model.getLat();
        this.lng = model.getLng();
    }

    public String getLat() {
        return this.lat;
    }

    public String getLng() {
        return this.lng;
    }

    @Override
    public String toString() {
        final String template = "CoordenadaDTO[lat='%s', lng='%s']";
        return String.format(template, this.lat, this.lng);
    }
}
