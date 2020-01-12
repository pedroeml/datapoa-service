package com.service.datapoa.crud.pontotaxi.dao.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PontoTaxi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lat;
    private String lng;
    private String registerTime;

    protected PontoTaxi() { }

    public PontoTaxi(String name, String lat, String lng, String registerTime) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.registerTime = registerTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getRegisterTime() {
        return registerTime;
    }

    @Override
    public String toString() {
        final String template = "PontoTaxi[id=%d, name='%s', lat='%s', lng='%s', registerTime='%s']";
        return String.format(template, this.id, this.name, this.lat, this.lng, this.registerTime);
    }
}
