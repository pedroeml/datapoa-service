package com.service.datapoa.crud.linhaonibus.model;

import com.service.datapoa.crud.linhaonibus.dao.jpa.LinhaOnibus;
import com.service.datapoa.crud.linhaonibus.integration.LinhaOnibusResponse;

public class LinhaOnibusModel {
    private final Integer id;
    private final String codigo;
    private final String nome;

    public LinhaOnibusModel(LinhaOnibus dao) {
        this.id = dao.getId();
        this.codigo = dao.getCodigo();
        this.nome = dao.getNome();
    }

    public LinhaOnibusModel(LinhaOnibusResponse response) {
        this.id = Integer.parseInt(response.getId());
        this.codigo = response.getCodigo();
        this.nome = response.getNome();
    }

    public LinhaOnibusModel(String codigo, String nome) {
        this.id = null;
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getId() {
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
