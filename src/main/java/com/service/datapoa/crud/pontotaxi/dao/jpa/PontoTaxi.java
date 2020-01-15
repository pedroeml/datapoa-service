package com.service.datapoa.crud.pontotaxi.dao.jpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PONTOS_TAXI")
public class PontoTaxi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "LAT", nullable = false)
    private String lat;
    @Column(name = "LNG", nullable = false)
    private String lng;
    @Column(name = "REGISTER_TIME", nullable = false)
    private String registerTime;

    protected PontoTaxi() { }

    public PontoTaxi(String name, String lat, String lng, String registerTime) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.registerTime = registerTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        final String template = "PontoTaxi[id=%d, name='%s', lat='%s', lng='%s', registerTime='%s']";
        return String.format(template, this.id, this.name, this.lat, this.lng, this.registerTime);
    }
}
