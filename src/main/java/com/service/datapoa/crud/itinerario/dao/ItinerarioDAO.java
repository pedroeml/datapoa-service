package com.service.datapoa.crud.itinerario.dao;

import com.service.datapoa.crud.Dao;
import com.service.datapoa.crud.itinerario.dao.jpa.Itinerario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class ItinerarioDAO extends JdbcDaoSupport implements Dao<List<Itinerario>> {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        this.setDataSource(dataSource);
    }

    private List<Itinerario> find(Itinerario itinerario) {
        final String query = "SELECT * FROM ITINERARIO_UT WHERE ID_LINHA = ? AND LAT = ? AND LNG = ?";
        final Object[] entries = new Object[]{itinerario.getIdLinha(), itinerario.getLat(), itinerario.getLng()};
        List<Itinerario> itinerarios;
        try {
            itinerarios = this.getJdbcTemplate().query(query, entries, new BeanPropertyRowMapper<>(Itinerario.class));
        } catch (DataAccessException e) {
            itinerarios = Collections.emptyList();
        }

        return itinerarios;
    }

    @Override
    public List<Itinerario> get(long id) {
        final String query = "SELECT * FROM ITINERARIO_UT WHERE ID_LINHA = ?";
        List<Itinerario> itinerario;
        try {
            itinerario = this.getJdbcTemplate().query(query, new Object[]{id}, new BeanPropertyRowMapper<>(Itinerario.class));
        } catch (DataAccessException e) {
            itinerario = Collections.emptyList();
        }

        return itinerario;
    }

    @Override
    public List<List<Itinerario>> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(List<Itinerario> itinerarios) {
        final String query = "INSERT INTO ITINERARIO_UT (ID_LINHA, LAT, LNG) VALUES (?, ?, ?)";

        itinerarios.parallelStream()
            .distinct()
            .filter(itinerario -> {
                final List<Itinerario> existingItinerarios = this.find(itinerario);

                if (!existingItinerarios.isEmpty()) {
                    itinerario.setId(existingItinerarios.get(0).getId());
                }

                return existingItinerarios.isEmpty();
            })
            .forEach(itinerario -> {
                final Object[] entries = new Object[]{itinerario.getIdLinha(), itinerario.getLat(), itinerario.getLng()};
                try {
                    this.getJdbcTemplate().update(query, entries);
                } catch (DataAccessException e) {
                    e.printStackTrace();
                } finally {
                    final Itinerario insertedItinerario = this.find(itinerario).get(0);
                    itinerario.setId(insertedItinerario.getId());
                }
            });
    }

    @Override
    public void update(List<Itinerario> itinerarios, String[] params) {
        this.delete(itinerarios);
        this.save(itinerarios);
    }

    @Override
    public void delete(List<Itinerario> itinerarios) {
        itinerarios.parallelStream()
            .map(Itinerario::getIdLinha)
            .distinct()
            .forEach(this::deleteByIdLinha);
    }

    public void deleteByIdLinha(Integer idLinha) {
        final String query = "DELETE FROM ITINERARIO_UT WHERE ID_LINHA = ?";
        final Object[] entries = new Object[]{idLinha};
        try {
            this.getJdbcTemplate().update(query, entries);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
