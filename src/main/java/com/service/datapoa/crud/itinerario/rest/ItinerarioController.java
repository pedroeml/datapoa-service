package com.service.datapoa.crud.itinerario.rest;

import com.service.datapoa.crud.itinerario.dto.ItinerarioDTO;
import com.service.datapoa.crud.itinerario.mapper.ItinerarioMapper;
import com.service.datapoa.crud.itinerario.model.ItinerarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/itinerario")
public class ItinerarioController {

    @Autowired
    private ItinerarioService service;

    @GetMapping(value = "/{id}")
    public ItinerarioDTO findById(@Valid @PathVariable long id) {
        final ItinerarioModel itinerario = this.service.findById(id);

        return ItinerarioMapper.mapToDTO(itinerario);
    }
}
