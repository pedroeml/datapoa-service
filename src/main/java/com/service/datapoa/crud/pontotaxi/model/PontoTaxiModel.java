package com.service.datapoa.crud.pontotaxi.model;

import com.service.datapoa.crud.pontotaxi.dao.jpa.PontoTaxi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PontoTaxiModel {
    private final Integer id;
    private final String name;
    private final String lat;
    private final String lng;
    private final String registerTime;

    public PontoTaxiModel(PontoTaxi dao) {
        this.id = dao.getId();
        this.name = dao.getName();
        this.lat = dao.getLat();
        this.lng = dao.getLng();
        this.registerTime = dao.getRegisterTime();
    }

    public PontoTaxiModel(String name, String lat, String lng) {
        this.id = null;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.registerTime = this.getUTCDate();
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

    private String getUTCDate() {
        final Date date = new Date();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        return dateFormatter.format(date);
    }

    @Override
    public String toString() {
        final String template = "PontoTaxiModel[id=%d, name='%s', lat='%s', lng='%s', registerTime='%s']";
        return String.format(template, this.id, this.name, this.lat, this.lng, this.registerTime);
    }
}
