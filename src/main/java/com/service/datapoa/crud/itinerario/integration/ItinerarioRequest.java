package com.service.datapoa.crud.itinerario.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItinerarioRequest {
    private String id;
    private String codigo;
    private String nome;
    private List<CoordenadaRequest> coordenadas;

    public ItinerarioRequest() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CoordenadaRequest> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<CoordenadaRequest> coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public String toString() {
        final String template = "ItinerarioRequest[id=%s, codigo='%s', nome='%s']";
        return String.format(template, this.id, this.codigo, this.nome);
    }
}
