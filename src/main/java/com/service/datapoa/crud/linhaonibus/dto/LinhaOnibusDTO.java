package com.service.datapoa.crud.linhaonibus.dto;

import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;

public class LinhaOnibusDTO {
    private final Integer id;
    private final String codigo;
    private final String nome;

    public LinhaOnibusDTO(LinhaOnibusModel model) {
        this.id = model.getId();
        this.codigo = model.getCodigo();
        this.nome = model.getNome();
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
        final String template = "LinhaOnibusDTO[id=%d, codigo='%s', nome='%s']";
        return String.format(template, this.id, this.codigo, this.nome);
    }
}
