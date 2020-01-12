package com.service.datapoa.crud.linhaonibus.model;

import com.service.datapoa.crud.linhaonibus.integration.LinhaOnibusResponse;

public class LinhaOnibusModel {
    private final String id;
    private final String codigo;
    private final String nome;

    public LinhaOnibusModel(String id, String codigo, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
    }

    public LinhaOnibusModel(LinhaOnibusResponse response) {
        this.id = response.getId();
        this.codigo = response.getCodigo();
        this.nome = response.getNome();
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

    @Override
    public String toString() {
        final String template = "LinhaOnibusModel[id='%s', codigo='%s', nome='%s']";
        return String.format(template, this.id, this.codigo, this.nome);
    }
}
