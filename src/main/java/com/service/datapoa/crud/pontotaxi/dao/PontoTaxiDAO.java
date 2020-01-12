package com.service.datapoa.crud.pontotaxi.dao;

import com.service.datapoa.crud.Dao;
import com.service.datapoa.crud.pontotaxi.dao.persistence.PontoTaxiPersistence;
import com.service.datapoa.crud.pontotaxi.dao.jpa.PontoTaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class PontoTaxiDAO implements Dao<PontoTaxi> {
    private final PontoTaxiRepository repository;

    @Autowired
    public PontoTaxiDAO(PontoTaxiRepository repository) {
        this.repository = repository;
        PontoTaxiPersistence.readBackupFile()
            .forEach(this::save);
    }

    @Override
    public PontoTaxi get(long id) {
        return this.repository.findById(id);
    }

    public List<PontoTaxi> getByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public List<PontoTaxi> getAll() {
        final Iterable<PontoTaxi> iterator = this.repository.findAll();
        return StreamSupport.stream(iterator.spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public void save(PontoTaxi pontoTaxi) {
        this.repository.save(pontoTaxi);
    }

    @Override
    public void update(PontoTaxi pontoTaxi, String[] params) {
        final String name = params[0] == null ? pontoTaxi.getName() : params[0];
        final String lat = params[1] == null ? pontoTaxi.getLat() : params[1];
        final String lng = params[2] == null ? pontoTaxi.getLng() : params[2];
        pontoTaxi.setName(name);
        pontoTaxi.setLat(lat);
        pontoTaxi.setLng(lng);
        repository.save(pontoTaxi);
    }

    @Override
    public void delete(PontoTaxi pontoTaxi) {
        this.repository.delete(pontoTaxi);
    }
}
