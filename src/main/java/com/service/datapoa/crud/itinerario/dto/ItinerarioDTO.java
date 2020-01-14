package com.service.datapoa.crud.itinerario.dto;

import com.service.datapoa.crud.itinerario.model.ItinerarioModel;

import java.util.List;
import java.util.stream.Collectors;

public class ItinerarioDTO {
    private final String id;
    private final String codigo;
    private final String nome;
    private final List<CoordenadaDTO> coordenadas;

    public ItinerarioDTO(ItinerarioModel model) {
        this.id = model.getId();
        this.codigo = model.getCodigo();
        this.nome = model.getNome();
        this.coordenadas = model.getCoordenadas().stream()
            .map(CoordenadaDTO::new)
            .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public List<CoordenadaDTO> getCoordenadas() {
        return coordenadas;
    }

    @Override
    public String toString() {
        final String template = "ItinerarioDTO[id='%s', codigo='%s', nome='%s']";
        return String.format(template, this.id, this.codigo, this.nome);
    }
}
