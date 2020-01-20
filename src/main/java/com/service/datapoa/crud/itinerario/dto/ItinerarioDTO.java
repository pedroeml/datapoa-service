package com.service.datapoa.crud.itinerario.dto;

import com.service.datapoa.crud.itinerario.model.ItinerarioModel;

import java.util.List;
import java.util.stream.Collectors;

public class ItinerarioDTO {
    private final Integer id;
    private final List<CoordenadaDTO> coordenadas;

    public ItinerarioDTO(ItinerarioModel model) {
        this.id = model.getId();
        this.coordenadas = model.getCoordenadas().stream()
            .map(CoordenadaDTO::new)
            .collect(Collectors.toList());
    }

    public Integer getId() {
        return this.id;
    }

    public List<CoordenadaDTO> getCoordenadas() {
        return this.coordenadas;
    }

    @Override
    public String toString() {
        final String template = "ItinerarioDTO[id='%s', coordenadas.size=%d]";
        return String.format(template, this.id, this.coordenadas.size());
    }
}
