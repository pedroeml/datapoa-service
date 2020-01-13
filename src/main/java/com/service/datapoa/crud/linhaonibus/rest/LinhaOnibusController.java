package com.service.datapoa.crud.linhaonibus.rest;

import com.service.datapoa.crud.linhaonibus.dto.LinhaOnibusDTO;
import com.service.datapoa.crud.linhaonibus.integration.LinhaOnibusRequest;
import com.service.datapoa.crud.linhaonibus.mapper.LinhaOnibusMapper;
import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/linhasOnibus")
public class LinhaOnibusController {

    @Autowired
    private LinhaOnibusService service;

    @GetMapping(value = "/{id}")
    public LinhaOnibusDTO findById(@Valid @PathVariable long id) {
        final LinhaOnibusModel linha = this.service.findById(id);

        return LinhaOnibusMapper.mapToDTO(linha);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LinhaOnibusDTO> linhas() {
        final List<LinhaOnibusModel> linhas = this.service.findAll();

        return linhas.stream()
            .map(LinhaOnibusMapper::mapToDTO)
            .collect(Collectors.toList());
    }

    @PostMapping(value = "")
    public LinhaOnibusDTO add(@RequestBody LinhaOnibusRequest request) {
        LinhaOnibusModel linha = LinhaOnibusMapper.mapFromRequest(request);
        linha = this.service.add(linha);

        return LinhaOnibusMapper.mapToDTO(linha);
    }

    @PatchMapping(value = "/{id}")
    public LinhaOnibusDTO update(@Valid @PathVariable long id, @RequestBody LinhaOnibusRequest request) {
        LinhaOnibusModel linha = LinhaOnibusMapper.mapFromRequest(request);
        linha = this.service.update(id, linha);

        return LinhaOnibusMapper.mapToDTO(linha);
    }

    @PutMapping(value = "/{id}")
    public LinhaOnibusDTO replace(@Valid @PathVariable long id, @RequestBody LinhaOnibusRequest request) {
        LinhaOnibusModel linha = LinhaOnibusMapper.mapFromRequest(request);
        linha = this.service.replace(id, linha);

        return LinhaOnibusMapper.mapToDTO(linha);
    }

    @DeleteMapping(value = "/{id}")
    public LinhaOnibusDTO delete(@Valid @PathVariable long id) {
        final LinhaOnibusModel linha = this.service.delete(id);

        return LinhaOnibusMapper.mapToDTO(linha);
    }
}
