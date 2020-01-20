package com.service.datapoa.crud.itinerario.model;

import java.util.List;

public class ItinerarioModel {
    private final Integer id;
    private final List<CoordenadaModel> coordenadas;

    public ItinerarioModel(Integer id, List<CoordenadaModel> coordenadas) {
        this.id = id;
        this.coordenadas = coordenadas;
    }

    public Integer getId() {
        return id;
    }

    public List<CoordenadaModel> getCoordenadas() {
        return coordenadas;
    }

    @Override
    public String toString() {
        final String template = "ItinerarioModel[id=%d, coordenadas.size=%d]";
        return String.format(template, this.id, this.coordenadas.size());
    }
}
