package com.service.datapoa.crud.linhaonibus.dao;

import com.service.datapoa.crud.Dao;
import com.service.datapoa.crud.linhaonibus.dao.jpa.LinhaOnibus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class LinhaOnibusDAO extends JdbcDaoSupport implements Dao<LinhaOnibus> {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        this.setDataSource(dataSource);
    }

    private LinhaOnibus find(LinhaOnibus linhaOnibus) {
        final String query = "SELECT * FROM LINHAS_ONIBUS WHERE CODIGO = ? AND NOME = ?";
        final Object[] entries = new Object[]{linhaOnibus.getCodigo(), linhaOnibus.getNome()};
        LinhaOnibus linha;
        try {
            linha = this.getJdbcTemplate().queryForObject(query, entries, new BeanPropertyRowMapper<>(LinhaOnibus.class));
        } catch (EmptyResultDataAccessException e) {
            linha = null;
        }

        return linha;
    }

    @Override
    public LinhaOnibus get(long id) {
        final String query = "SELECT * FROM LINHAS_ONIBUS WHERE ID = ?";
        LinhaOnibus linha;
        try {
            linha = this.getJdbcTemplate().queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(LinhaOnibus.class));
        } catch (EmptyResultDataAccessException e) {
            linha = null;
        }

        return linha;
    }

    @Override
    public List<LinhaOnibus> getAll() {
        final String query = "SELECT * FROM LINHAS_ONIBUS";
        List<LinhaOnibus> linhas;
        try {
            linhas = this.getJdbcTemplate().query(query, new BeanPropertyRowMapper<>(LinhaOnibus.class));
        } catch (DataAccessException e) {
            linhas = Collections.emptyList();
        }

        return linhas;
    }

    @Override
    public void save(LinhaOnibus linhaOnibus) {
        LinhaOnibus linha = this.find(linhaOnibus);

        if (linha == null) {
            final String query = "INSERT INTO LINHAS_ONIBUS (ID, CODIGO, NOME) VALUES (?, ?, ?)";
            final Object[] entries = new Object[]{linhaOnibus.getId(), linhaOnibus.getCodigo(), linhaOnibus.getNome()};
            try {
                this.getJdbcTemplate().update(query, entries);
            } catch (DataAccessException e) {
                e.printStackTrace();
            } finally {
                linha = this.find(linhaOnibus);
            }
        }

        linhaOnibus.setId(linha.getId());
    }

    @Override
    public void update(LinhaOnibus linhaOnibus, String[] params) {
        linhaOnibus.setCodigo(params[0] == null ? linhaOnibus.getCodigo() : params[0]);
        linhaOnibus.setNome(params[1] == null ? linhaOnibus.getNome() : params[1]);

        final String query = "UPDATE LINHAS_ONIBUS SET CODIGO = ?, NOME = ? WHERE ID = ?";
        final Object[] entries = new Object[]{linhaOnibus.getCodigo(), linhaOnibus.getNome(), linhaOnibus.getId()};
        try {
            this.getJdbcTemplate().update(query, entries);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(LinhaOnibus linhaOnibus) {
        final String query = "DELETE FROM LINHAS_ONIBUS WHERE ID = ?";
        final Object[] entries = new Object[]{linhaOnibus.getId()};
        try {
            this.getJdbcTemplate().update(query, entries);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
