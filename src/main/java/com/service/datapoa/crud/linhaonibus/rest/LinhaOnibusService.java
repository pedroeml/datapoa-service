package com.service.datapoa.crud.linhaonibus.rest;

import com.service.datapoa.crud.linhaonibus.integration.LinhaOnibusResponse;
import com.service.datapoa.crud.linhaonibus.mapper.LinhaOnibusMapper;
import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinhaOnibusService {
    private static final String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

    @Autowired
    private RestTemplate restTemplate;

    public List<LinhaOnibusModel> findAll() {
        final ResponseEntity<List<LinhaOnibusResponse>> response = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<LinhaOnibusResponse>>() { });
        final List<LinhaOnibusResponse> linhasResponse = response.getBody();

        return linhasResponse == null ? Collections.emptyList() : linhasResponse.stream()
            .map(LinhaOnibusMapper::mapToModel)
            .collect(Collectors.toList());
    }
}
