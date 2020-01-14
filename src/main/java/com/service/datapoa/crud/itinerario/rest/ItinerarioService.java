package com.service.datapoa.crud.itinerario.rest;

import com.service.datapoa.crud.Crud;
import com.service.datapoa.crud.itinerario.mapper.ItinerarioMapper;
import com.service.datapoa.crud.itinerario.model.ItinerarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ItinerarioService implements Crud<ItinerarioModel> {
    private static final String urlTemplate = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=%s";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ItinerarioModel findById(long id) {
        final String url = String.format(urlTemplate, id);
        final ResponseEntity<Map<String, Object>> untypedResponse = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() { });
        final Map<String, Object> body = untypedResponse.getBody();
        return ItinerarioMapper.mapFromUntypedResponse(body);
    }

    @Override
    public List<ItinerarioModel> findAll() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItinerarioModel add(ItinerarioModel itinerarioModel) {
        // TODO: Implementation
        return null;
    }

    @Override
    public ItinerarioModel update(long id, ItinerarioModel itinerarioModel) {
        // TODO: Implementation
        return null;
    }

    @Override
    public ItinerarioModel replace(long id, ItinerarioModel itinerarioModel) {
        // TODO: Implementation
        return null;
    }

    @Override
    public ItinerarioModel delete(long id) {
        // TODO: Implementation
        return null;
    }
}
