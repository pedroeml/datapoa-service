package com.service.datapoa.crud.pontotaxi.dto;

import com.service.datapoa.crud.pontotaxi.model.PontoTaxiModel;

public class PontoTaxiDTO {
    private final Integer id;
    private final String name;
    private final String lat;
    private final String lng;
    private final String registerTime;

    public PontoTaxiDTO(PontoTaxiModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.lat = model.getLat();
        this.lng = model.getLng();
        this.registerTime = model.getRegisterTime();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    @Override
    public String toString() {
        final String template = "PontoTaxiDTO[id=%d, name='%s', lat='%s', lng='%s', registerTime='%s']";
        return String.format(template, this.id, this.name, this.lat, this.lng, this.registerTime);
    }
}
