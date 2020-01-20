package com.service.datapoa.crud.itinerario.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItinerarioRequest {
    private Integer id;
    private List<CoordenadaRequest> coordenadas;

    public ItinerarioRequest() { }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CoordenadaRequest> getCoordenadas() {
        return this.coordenadas;
    }

    public void setCoordenadas(List<CoordenadaRequest> coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public String toString() {
        final String template = "ItinerarioRequest[id=%d, coordenadas.size=%d]";
        return String.format(template, this.id, this.coordenadas != null ? this.coordenadas.size() : 0);
    }
}
