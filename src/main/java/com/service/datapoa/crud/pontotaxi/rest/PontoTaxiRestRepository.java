package com.service.datapoa.crud.pontotaxi.rest;

import com.service.datapoa.crud.pontotaxi.jpa.PontoTaxi;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "pontosTaxi", path = "pontosTaxi")
public interface PontoTaxiRestRepository extends PagingAndSortingRepository<PontoTaxi, Long> {
    List<PontoTaxi> findByName(@Param("name") String name);
}
