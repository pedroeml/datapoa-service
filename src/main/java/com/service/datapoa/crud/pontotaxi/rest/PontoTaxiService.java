package com.service.datapoa.crud.pontotaxi.rest;

import com.service.datapoa.crud.Crud;
import com.service.datapoa.crud.pontotaxi.dao.jpa.PontoTaxi;
import com.service.datapoa.crud.pontotaxi.dao.PontoTaxiDAO;
import com.service.datapoa.crud.pontotaxi.mapper.PontoTaxiMapper;
import com.service.datapoa.crud.pontotaxi.model.PontoTaxiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoTaxiService implements Crud<PontoTaxiModel> {

    @Autowired
    private PontoTaxiDAO dao;

    private PontoTaxi getById(long id) throws ResponseStatusException {
        final PontoTaxi ponto = this.dao.get(id);

        if (ponto == null) {
            final String reason = String.format("PontoTaxi ID %d not found.", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, reason);
        }

        return ponto;
    }

    @Override
    public PontoTaxiModel findById(long id) {
        final PontoTaxi ponto = this.getById(id);

        return PontoTaxiMapper.mapToModel(ponto);
    }

    @Override
    public List<PontoTaxiModel> findAll() {
        final List<PontoTaxi> pontos = this.dao.getAll();

        return pontos.stream()
            .map(PontoTaxiMapper::mapToModel)
            .collect(Collectors.toList());
    }

    @Override
    public PontoTaxiModel add(PontoTaxiModel model) {
        final PontoTaxi ponto = PontoTaxiMapper.mapToDAO(model);
        this.dao.save(ponto);

        return new PontoTaxiModel(ponto);
    }

    @Override
    public PontoTaxiModel update(long id, PontoTaxiModel model) {
        final PontoTaxi ponto = this.getById(id);
        final String[] params = { model.getName(), model.getLat(), model.getLng() };
        this.dao.update(ponto, params);

        return new PontoTaxiModel(ponto);
    }

    @Override
    public PontoTaxiModel replace(long id, PontoTaxiModel model) {
        final PontoTaxiModel ponto = this.delete(id);
        return this.add(model);
    }

    @Override
    public PontoTaxiModel delete(long id) {
        final PontoTaxi ponto = this.getById(id);
        this.dao.delete(ponto);

        return new PontoTaxiModel(ponto);
    }
}
