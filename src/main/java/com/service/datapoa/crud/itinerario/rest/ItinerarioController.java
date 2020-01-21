package com.service.datapoa.crud.itinerario.rest;

import com.service.datapoa.crud.itinerario.dto.ItinerarioDTO;
import com.service.datapoa.crud.itinerario.integration.ItinerarioRequest;
import com.service.datapoa.crud.itinerario.mapper.ItinerarioMapper;
import com.service.datapoa.crud.itinerario.model.ItinerarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "")
    public ItinerarioDTO add(@RequestBody ItinerarioRequest request) {
        ItinerarioModel itinerario = ItinerarioMapper.mapFromRequest(request);
        itinerario = this.service.add(itinerario);

        return ItinerarioMapper.mapToDTO(itinerario);
    }

    @PatchMapping(value ="/{id}")
    public ItinerarioDTO update(@Valid @PathVariable long id, @RequestBody ItinerarioRequest request) {
        ItinerarioModel itinerario = ItinerarioMapper.mapFromRequest(request);
        itinerario = this.service.update(id, itinerario);

        return ItinerarioMapper.mapToDTO(itinerario);
    }

    @PutMapping(value = "/{id}")
    public ItinerarioDTO replace(@Valid @PathVariable long id, @RequestBody ItinerarioRequest request) {
        ItinerarioModel itinerario = ItinerarioMapper.mapFromRequest(request);
        itinerario = this.service.replace(id, itinerario);

        return ItinerarioMapper.mapToDTO(itinerario);
    }

    @DeleteMapping(value = "/{id}")
    public ItinerarioDTO delete(@Valid @PathVariable long id) {
        final ItinerarioModel itinerario = this.service.delete(id);

        return ItinerarioMapper.mapToDTO(itinerario);
    }
}
