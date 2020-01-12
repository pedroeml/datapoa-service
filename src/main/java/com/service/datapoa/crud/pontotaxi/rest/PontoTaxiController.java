package com.service.datapoa.crud.pontotaxi.rest;

import com.service.datapoa.crud.pontotaxi.dto.PontoTaxiDTO;
import com.service.datapoa.crud.pontotaxi.integration.PontoTaxiRequest;
import com.service.datapoa.crud.pontotaxi.mapper.PontoTaxiMapper;
import com.service.datapoa.crud.pontotaxi.model.PontoTaxiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pontosTaxi")
public class PontoTaxiController {

    @Autowired
    private PontoTaxiService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PontoTaxiDTO> findAll() {
        final List<PontoTaxiModel> pontos = this.service.findAll();

        return pontos.stream()
            .map(PontoTaxiMapper::mapToDTO)
            .collect(Collectors.toList());
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PontoTaxiDTO add(@RequestBody PontoTaxiRequest request) {
        PontoTaxiModel ponto = PontoTaxiMapper.mapFromRequest(request);
        ponto = this.service.add(ponto);

        return PontoTaxiMapper.mapToDTO(ponto);
    }
}
