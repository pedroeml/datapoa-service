package com.service.datapoa.crud.linhaonibus.dao.jpa;

import javax.persistence.*;

@Entity
@Table(name = "LINHAS_ONIBUS")
public class LinhaOnibus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "NOME")
    private String nome;

    protected LinhaOnibus() { }

    public LinhaOnibus(String id, String codigo, String nome) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
    }

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
        final String template = "LinhaOnibus[id=%s, codigo='%s', nome='%s']";
        return String.format(template, this.id, this.codigo, this.nome);
    }
}
