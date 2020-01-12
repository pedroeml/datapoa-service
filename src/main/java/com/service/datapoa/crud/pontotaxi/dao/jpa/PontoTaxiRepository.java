package com.service.datapoa.crud.pontotaxi.dao.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PontoTaxiRepository extends CrudRepository<PontoTaxi, Long> {
    PontoTaxi findById(long id);
    List<PontoTaxi> findByName(String name);
}