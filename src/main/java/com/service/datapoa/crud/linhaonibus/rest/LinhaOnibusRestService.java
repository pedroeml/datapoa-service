package com.service.datapoa.crud.linhaonibus.rest;

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
public class LinhaOnibusRestService {
    private static final String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

    @Autowired
    private RestTemplate restTemplate;

    protected LinhaOnibusModel findById(long id) {
        List<LinhaOnibusModel> linhas = this.findAll();
        final Optional<LinhaOnibusModel> maybeLinha = linhas.parallelStream()
            .filter(linhaOnibusModel -> linhaOnibusModel.getId() == id)
            .findFirst();

        if (maybeLinha.isEmpty()) {
            final String reason = String.format("LinhaOnibus ID %d not found.", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        return maybeLinha.get();
    }

    protected List<LinhaOnibusModel> findAll() {
        final ResponseEntity<List<LinhaOnibusResponse>> response = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<LinhaOnibusResponse>>() { });
        final List<LinhaOnibusResponse> linhasResponse = response.getBody();

        return linhasResponse == null ? Collections.emptyList() : linhasResponse.parallelStream()
            .map(LinhaOnibusMapper::mapFromResponse)
            .collect(Collectors.toList());
    }
}
