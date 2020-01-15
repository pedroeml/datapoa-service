package com.service.datapoa.crud.pontotaxi.dao;

import com.service.datapoa.crud.Dao;
import com.service.datapoa.crud.pontotaxi.dao.jpa.PontoTaxi;
import com.service.datapoa.crud.pontotaxi.dao.persistence.PontoTaxiPersistence;
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
public class PontoTaxiDAO extends JdbcDaoSupport implements Dao<PontoTaxi> {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        this.setDataSource(dataSource);
        PontoTaxiPersistence.readBackupFile().forEach(this::save);
    }

    private PontoTaxi find(PontoTaxi pontoTaxi) {
        final String query = "SELECT * FROM PONTOS_TAXI WHERE NAME = ? AND LAT = ? AND LNG = ?";
        final Object[] entries = new Object[]{pontoTaxi.getName(), pontoTaxi.getLat(), pontoTaxi.getLng()};
        PontoTaxi ponto;
        try {
            ponto = this.getJdbcTemplate().queryForObject(query, entries, new BeanPropertyRowMapper<>(PontoTaxi.class));
        } catch (EmptyResultDataAccessException e) {
            ponto = null;
        }

        return ponto;
    }

    @Override
    public PontoTaxi get(long id) {
        final String query = "SELECT * FROM PONTOS_TAXI WHERE ID = ?";
        PontoTaxi ponto;
        try {
            ponto = this.getJdbcTemplate().queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(PontoTaxi.class));
        } catch (EmptyResultDataAccessException e) {
            ponto = null;
        }

        return ponto;
    }

    @Override
    public List<PontoTaxi> getAll() {
        final String query = "SELECT * FROM PONTOS_TAXI";
        List<PontoTaxi> pontos;
        try {
            pontos = this.getJdbcTemplate().query(query, new BeanPropertyRowMapper<>(PontoTaxi.class));
        } catch (DataAccessException e) {
            pontos = Collections.emptyList();
        }

        return pontos;
    }

    @Override
    public void save(PontoTaxi pontoTaxi) {
        PontoTaxi ponto = this.find(pontoTaxi);

        if (ponto == null) {
            final String query = "INSERT INTO PONTOS_TAXI (NAME, LAT, LNG, REGISTER_TIME) VALUES (?, ?, ?, ?)";
            final Object[] entries = new Object[]{pontoTaxi.getName(), pontoTaxi.getLat(), pontoTaxi.getLng(), pontoTaxi.getRegisterTime()};
            try {
                this.getJdbcTemplate().update(query, entries);
            } catch (DataAccessException e) {
                e.printStackTrace();
            } finally {
                ponto = this.find(pontoTaxi);
            }
        }

        pontoTaxi.setId(ponto.getId());
        pontoTaxi.setRegisterTime(ponto.getRegisterTime());
        // TODO: The line below shutdowns the application. Maybe Spring doesn't let to write to files in /resources?
        // PontoTaxiPersistence.appendBackupFile(pontoTaxi);
    }

    @Override
    public void update(PontoTaxi pontoTaxi, String[] params) {
        pontoTaxi.setName(params[0] == null ? pontoTaxi.getName() : params[0]);
        pontoTaxi.setLat(params[1] == null ? pontoTaxi.getLat() : params[1]);
        pontoTaxi.setLng(params[2] == null ? pontoTaxi.getLng() : params[2]);

        final String query = "UPDATE PONTOS_TAXI SET NAME = ?, LAT = ?, LNG = ? WHERE ID = ?";
        final Object[] entries = new Object[]{pontoTaxi.getName(), pontoTaxi.getLat(), pontoTaxi.getLng(), pontoTaxi.getId()};
        try {
            this.getJdbcTemplate().update(query, entries);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PontoTaxi pontoTaxi) {
        final String query = "DELETE FROM PONTOS_TAXI WHERE ID = ?";
        final Object[] entries = new Object[]{pontoTaxi.getId()};
        try {
            this.getJdbcTemplate().update(query, entries);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
