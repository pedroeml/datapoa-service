package com.service.datapoa.crud.pontotaxi.rest;

import com.service.datapoa.crud.pontotaxi.dto.PontoTaxiDTO;
import com.service.datapoa.crud.pontotaxi.integration.PontoTaxiRequest;
import com.service.datapoa.crud.pontotaxi.mapper.PontoTaxiMapper;
import com.service.datapoa.crud.pontotaxi.model.PontoTaxiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pontosTaxi")
public class PontoTaxiController {

    @Autowired
    private PontoTaxiService service;

    @GetMapping(value = "/{id}")
    public PontoTaxiDTO findById(@Valid @PathVariable long id) {
        final PontoTaxiModel ponto = this.service.findById(id);

        return PontoTaxiMapper.mapToDTO(ponto);
    }

    @GetMapping(value = "")
    public List<PontoTaxiDTO> findAll() {
        final List<PontoTaxiModel> pontos = this.service.findAll();

        return pontos.stream()
            .map(PontoTaxiMapper::mapToDTO)
            .collect(Collectors.toList());
    }

    @PostMapping(value = "")
    public PontoTaxiDTO add(@RequestBody PontoTaxiRequest request) {
        PontoTaxiModel ponto = PontoTaxiMapper.mapFromRequest(request);
        ponto = this.service.add(ponto);

        return PontoTaxiMapper.mapToDTO(ponto);
    }

    @PatchMapping(value = "/{id}")
    public PontoTaxiDTO update(@Valid @PathVariable long id, @RequestBody PontoTaxiRequest request) {
        PontoTaxiModel ponto = PontoTaxiMapper.mapFromRequest(request);
        ponto = this.service.update(id, ponto);

        return PontoTaxiMapper.mapToDTO(ponto);
    }

    @PutMapping(value = "/{id}")
    public PontoTaxiDTO replace(@Valid @PathVariable long id, @RequestBody PontoTaxiRequest request) {
        PontoTaxiModel ponto = PontoTaxiMapper.mapFromRequest(request);
        ponto = this.service.replace(id, ponto);

        return PontoTaxiMapper.mapToDTO(ponto);
    }

    @DeleteMapping(value = "/{id}")
    public PontoTaxiDTO delete(@Valid @PathVariable long id) {
        final PontoTaxiModel ponto = this.service.delete(id);

        return PontoTaxiMapper.mapToDTO(ponto);
    }
}
