package com.service.datapoa.crud.pontotaxi.rest;

import com.service.datapoa.crud.pontotaxi.dao.PontoTaxi;
import com.service.datapoa.crud.pontotaxi.dao.PontoTaxiDAO;
import com.service.datapoa.crud.pontotaxi.mapper.PontoTaxiMapper;
import com.service.datapoa.crud.pontotaxi.model.PontoTaxiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoTaxiService {

    @Autowired
    private PontoTaxiDAO dao;

    public PontoTaxiModel findById(long id) {
        final PontoTaxi ponto = this.dao.get(id);

        return ponto != null ? PontoTaxiMapper.mapToModel(ponto) : null;
    }

    public List<PontoTaxiModel> findByName(String name) {
        final List<PontoTaxi> pontos = this.dao.getByName(name);

        return pontos.stream()
                .map(PontoTaxiMapper::mapToModel)
                .collect(Collectors.toList());
    }

    public List<PontoTaxiModel> findAll() {
        final List<PontoTaxi> pontos = this.dao.getAll();

        return pontos.stream()
            .map(PontoTaxiMapper::mapToModel)
            .collect(Collectors.toList());
    }

    public void add(PontoTaxiModel model) {
        final PontoTaxi ponto = new PontoTaxi(model.getName(), model.getLat(), model.getLng(), model.getRegisterTime());

        this.dao.save(ponto);
    }

    public void update(long id, PontoTaxiModel model) {
        final PontoTaxi ponto = this.dao.get(id);
        final String[] params = { model.getName(), model.getLat(), model.getLng() };
        this.dao.update(ponto, (String[]) params);
    }

    public void delete(long id) {
        final PontoTaxi ponto = this.dao.get(id);
        this.dao.delete(ponto);
    }
}
