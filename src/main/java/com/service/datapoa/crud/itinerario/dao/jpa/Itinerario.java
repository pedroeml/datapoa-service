package com.service.datapoa.crud.itinerario.dao.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ITINERARIO_UT")
public class Itinerario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name = "ID_LINHA", nullable = false)
    private int idLinha;
    @Column(name = "LAT", nullable = false)
    private String lat;
    @Column(name = "LNG", nullable = false)
    private String lng;

    protected Itinerario() { }

    public Itinerario(int idLinha, String lat, String lng) {
        this.idLinha = idLinha;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLinha() {
        return this.idLinha;
    }

    public void setIdLinha(int idLinha) {
        this.idLinha = idLinha;
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
        final String template = "Itinerario[id=%d, idLinha=%d, lat='%s', lng='%s']";
        return String.format(template, this.id, this.idLinha, this.lat, this.lng);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Itinerario)) {
            return false;
        }
        final Itinerario that = (Itinerario) o;
        return getIdLinha() == that.getIdLinha()
            && getLat().contentEquals(that.getLat())
            && getLng().contentEquals(that.getLng());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdLinha(), getLat(), getLng());
    }
}
