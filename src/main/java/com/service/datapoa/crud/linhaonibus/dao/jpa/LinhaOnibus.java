package com.service.datapoa.crud.linhaonibus.dao.jpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LINHAS_ONIBUS")
public class LinhaOnibus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private int id;
    @Column(name = "CODIGO", nullable = false)
    private String codigo;
    @Column(name = "NOME", nullable = false)
    private String nome;

    protected LinhaOnibus() { }

    public LinhaOnibus(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        final String template = "LinhaOnibus[id=%d, codigo='%s', nome='%s']";
        return String.format(template, this.id, this.codigo, this.nome);
    }
}
