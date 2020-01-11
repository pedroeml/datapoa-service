package com.service.datapoa.crud.linhaonibus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/linhasOnibus")
public class LinhaOnibusController {
    private static final String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
    private final List<HttpMessageConverter<?>> messageConverters;

    @Autowired
    private RestTemplate restTemplate;

    public LinhaOnibusController() {
        final List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        this.messageConverters = messageConverters;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LinhaOnibus> linhas() {
        this.restTemplate.setMessageConverters(this.messageConverters);
        final ResponseEntity<List<LinhaOnibus>> response = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<LinhaOnibus>>() { });
        final List<LinhaOnibus> linhas = response.getBody();

        return linhas;
    }
}
