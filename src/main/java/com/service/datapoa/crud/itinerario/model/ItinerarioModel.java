package com.service.datapoa.crud.itinerario.model;

import java.util.List;

public class ItinerarioModel {
    private final String id;
    private final String codigo;
    private final String nome;
    private final List<CoordenadaModel> coordenadas;

    public ItinerarioModel(String id, String codigo, String nome, List<CoordenadaModel> coordenadas) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.coordenadas = coordenadas;
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

    public List<CoordenadaModel> getCoordenadas() {
        return coordenadas;
    }

    @Override
    public String toString() {
        final String template = "ItinerarioModel[id='%s', codigo='%s', nome='%s']";
        return String.format(template, this.id, this.codigo, this.nome);
    }
}
