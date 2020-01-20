package com.service.datapoa.crud.itinerario.rest;

import com.service.datapoa.crud.itinerario.mapper.ItinerarioMapper;
import com.service.datapoa.crud.itinerario.model.ItinerarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class ItinerarioRestService {
    private static final String urlTemplate = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=%s";

    @Autowired
    private RestTemplate restTemplate;

    protected ItinerarioModel findById(long id) {
        final String url = String.format(urlTemplate, id);
        ItinerarioModel itinerario;
        try {
            final ResponseEntity<Map<String, Object>> untypedResponse = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, Object>>() { });
            final Map<String, Object> body = untypedResponse.getBody();
            itinerario = ItinerarioMapper.mapFromUntypedResponse(body);
        } catch (RestClientException e) {
            final String reason = String.format("Itinerario for LinhaOnibus ID %d not found.", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        return itinerario;
    }
}
