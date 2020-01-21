package com.service.datapoa.crud.itinerario.rest;

import com.service.datapoa.crud.Crud;
import com.service.datapoa.crud.itinerario.dao.ItinerarioDAO;
import com.service.datapoa.crud.itinerario.dao.jpa.Itinerario;
import com.service.datapoa.crud.itinerario.mapper.ItinerarioMapper;
import com.service.datapoa.crud.itinerario.model.ItinerarioModel;
import com.service.datapoa.crud.linhaonibus.dao.LinhaOnibusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItinerarioService implements Crud<ItinerarioModel> {

    @Autowired
    private ItinerarioDAO dao;

    @Autowired
    private LinhaOnibusDAO daoLinhaOnibus;

    @Autowired
    private ItinerarioRestService restService;

    @Override
    public ItinerarioModel findById(long id) {
        final List<Itinerario> itinerario = this.dao.get(id);
        return itinerario.isEmpty() ? this.restService.findById(id) : ItinerarioMapper.mapToModel(itinerario);
    }

    @Override
    public List<ItinerarioModel> findAll() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItinerarioModel add(ItinerarioModel itinerarioModel) {
        List<Itinerario> itinerarios = this.dao.get(itinerarioModel.getId());
        final ResponseStatusException e = this.validate(itinerarioModel.getId(), itinerarios, "save");

        if (e != null) {
            throw e;
        } else {
            itinerarios = ItinerarioMapper.mapToDAO(itinerarioModel);
            this.dao.save(itinerarios);
        }

        return ItinerarioMapper.mapToModel(itinerarios);
    }

    @Override
    public ItinerarioModel update(long id, ItinerarioModel itinerarioModel) {
        return this.replace(id, itinerarioModel);
    }

    @Override
    public ItinerarioModel replace(long id, ItinerarioModel itinerarioModel) {
        List<Itinerario> itinerarios = this.dao.get(id);
        final ResponseStatusException e = this.validate(id, itinerarios, "update");

        if (e != null) {
            throw e;
        } else {
            itinerarios = ItinerarioMapper.mapToDAO(itinerarioModel);
            this.dao.update(itinerarios, null);
        }

        return ItinerarioMapper.mapToModel(itinerarios);
    }

    @Override
    public ItinerarioModel delete(long id) {
        final List<Itinerario> itinerarios = this.dao.get(id);
        final ResponseStatusException e = this.validate(id, itinerarios, "delete");

        if (e != null) {
            throw e;
        } else {
            this.dao.delete(itinerarios);
        }

        return ItinerarioMapper.mapToModel(itinerarios);
    }

    private ResponseStatusException validate(long id, List<Itinerario> itinerarios, String operation) {
        ResponseStatusException e = null;

        if (itinerarios.isEmpty()) {
            if (this.daoLinhaOnibus.get(id) == null) {
                if (this.restService.findById(id) != null) {
                    final String template = "Itinerario for LinhaOnibus ID %d exists only on external DB, thus you can't %s it unless you store a LinhaOnibus with ID %d into our DB.";
                    final String reason = String.format(template, id, operation, id);
                    e = new ResponseStatusException(HttpStatus.UNAUTHORIZED, reason);
                }
            } else {
                final String template = "Itinerario for LinhaOnibus ID %d is empty, thus there's nothing to %s.";
                final String reason = String.format(template, id, operation);
                e = new ResponseStatusException(HttpStatus.NOT_MODIFIED, reason);
            }
        }

        return e;
    }
}
