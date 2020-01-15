package com.service.datapoa.crud.linhaonibus.rest;

import com.service.datapoa.crud.Crud;
import com.service.datapoa.crud.linhaonibus.dao.LinhaOnibusDAO;
import com.service.datapoa.crud.linhaonibus.dao.jpa.LinhaOnibus;
import com.service.datapoa.crud.linhaonibus.integration.LinhaOnibusResponse;
import com.service.datapoa.crud.linhaonibus.mapper.LinhaOnibusMapper;
import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LinhaOnibusService implements Crud<LinhaOnibusModel> {
    private static final String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LinhaOnibusDAO dao;

    @Override
    public LinhaOnibusModel findById(long id) {
        final LinhaOnibus linha = this.dao.get(id);
        LinhaOnibusModel linhaOnibus;

        if (linha == null) {
            List<LinhaOnibusModel> linhas = this.findAllFromExternalAPI();
            final Optional<LinhaOnibusModel> maybeLinha = linhas.parallelStream()
                .filter(linhaOnibusModel -> linhaOnibusModel.getId() == id)
                .findFirst();

            if (maybeLinha.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("PontoTaxi ID %d not found", id));
            }

            linhaOnibus = maybeLinha.get();
        } else {
            linhaOnibus = LinhaOnibusMapper.mapToModel(linha);
        }

        return linhaOnibus;
    }

    private List<LinhaOnibusModel> findAllFromExternalAPI() {
        final ResponseEntity<List<LinhaOnibusResponse>> response = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<LinhaOnibusResponse>>() { });
        final List<LinhaOnibusResponse> linhasResponse = response.getBody();

        return linhasResponse == null ? Collections.emptyList() : linhasResponse.stream()
                .map(LinhaOnibusMapper::mapFromResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LinhaOnibusModel> findAll() {
        // TODO: Add handler for finding and replacing objects with same ID in the MySQL DB
        return this.findAllFromExternalAPI();
    }

    @Override
    public LinhaOnibusModel add(LinhaOnibusModel linhaOnibusModel) {
        // TODO: Implementation
        return null;
    }

    @Override
    public LinhaOnibusModel update(long id, LinhaOnibusModel linhaOnibusModel) {
        // TODO: Implementation
        return null;
    }

    @Override
    public LinhaOnibusModel replace(long id, LinhaOnibusModel linhaOnibusModel) {
        // TODO: Implementation
        return null;
    }

    @Override
    public LinhaOnibusModel delete(long id) {
        // TODO: Call other DAO class for deleting entries on table ITINERARIO_UT with "DELETE FROM ITINERARIO_UT WHERE ID_LINHA = ?"
        // TODO: Implementation
        return null;
    }
}
