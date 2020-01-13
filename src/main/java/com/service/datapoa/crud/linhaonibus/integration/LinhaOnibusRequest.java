package com.service.datapoa.crud.linhaonibus.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinhaOnibusRequest {
    private String id;
    private String codigo;
    private String nome;

    public LinhaOnibusRequest() { }

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

    @Override
    public String toString() {
        final String template = "LinhaOnibusRequest[id=%s, codigo='%s', nome='%s']";
        return String.format(template, this.id, this.codigo, this.nome);
    }
}
