package com.service.datapoa.crud.linhaonibus.rest;

import com.service.datapoa.crud.Crud;
import com.service.datapoa.crud.linhaonibus.dao.LinhaOnibusDAO;
import com.service.datapoa.crud.linhaonibus.dao.jpa.LinhaOnibus;
import com.service.datapoa.crud.linhaonibus.mapper.LinhaOnibusMapper;
import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinhaOnibusService implements Crud<LinhaOnibusModel> {

    @Autowired
    private LinhaOnibusDAO dao;

    @Autowired
    private LinhaOnibusRestService restService;

    @Override
    public LinhaOnibusModel findById(long id) {
        final LinhaOnibus linha = this.dao.get(id);
        return linha == null ? this.restService.findById(id) : LinhaOnibusMapper.mapToModel(linha);
    }

    @Override
    public List<LinhaOnibusModel> findAll() {
        List<LinhaOnibusModel> linhasFromDB = this.dao.getAll().parallelStream()
            .map(LinhaOnibusMapper::mapToModel)
            .collect(Collectors.toList());
        List<LinhaOnibusModel> linhasFromAPI = this.restService.findAll().parallelStream()
            .filter(linha -> !linhasFromDB.contains(linha))
            .collect(Collectors.toList());

        linhasFromAPI.addAll(linhasFromDB);

        return linhasFromAPI;
    }

    @Override
    public LinhaOnibusModel add(LinhaOnibusModel model) {
        final List<LinhaOnibusModel> linhasFromAPI = this.restService.findAll().parallelStream()
            .filter(linha -> linha.getCodigo().contentEquals(model.getCodigo()) && linha.getNome().contentEquals(model.getNome()))
            .collect(Collectors.toList());

        if (!linhasFromAPI.isEmpty()) {
            final String reason = String.format("LinhaOnibus with properties [codigo='%s', nome='%s'] already exists.", model.getCodigo(), model.getNome());
            throw new ResponseStatusException(HttpStatus.CONFLICT, reason);
        }

        final LinhaOnibus linha = LinhaOnibusMapper.mapToDAO(model);
        this.dao.save(linha);

        return new LinhaOnibusModel(linha);
    }

    @Override
    public LinhaOnibusModel update(long id, LinhaOnibusModel model) {
        LinhaOnibus linha = this.dao.get(id);

        if (linha == null) {
            final LinhaOnibusModel linhaModel = this.restService.findById(id);
            final String codigo = model.getCodigo() == null ? linhaModel.getCodigo() : model.getCodigo();
            final String nome = model.getNome() == null ? linhaModel.getNome() : model.getNome();
            linha = new LinhaOnibus(codigo, nome);
            linha.setId(linhaModel.getId());
            this.dao.save(linha);
        } else {
            final String[] params = { model.getCodigo(), model.getNome() };
            this.dao.update(linha, params);
        }

        return new LinhaOnibusModel(linha);
    }

    @Override
    public LinhaOnibusModel replace(long id, LinhaOnibusModel model) {
        LinhaOnibus linha = this.dao.get(id);

        if (linha == null) {
            this.restService.findById(id);
        } else {
            this.delete(id);
        }

        return this.add(model);
    }

    @Override
    public LinhaOnibusModel delete(long id) {
        final LinhaOnibus linha = this.dao.get(id);

        if (linha == null) {
            final LinhaOnibusModel linhaFromAPI = this.restService.findById(id);
            if (linhaFromAPI != null) {
                final String reason = String.format("LinhaOnibus ID %d exists only on external DB, thus you can't delete it.", id);
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, reason);
            }
        } else {
            // TODO: Call other DAO class for deleting entries on table ITINERARIO_UT with "DELETE FROM ITINERARIO_UT WHERE ID_LINHA = ?"
            this.delete(id);
        }

        return LinhaOnibusMapper.mapToModel(linha);
    }
}
