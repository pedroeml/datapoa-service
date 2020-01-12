package com.service.datapoa.crud.pontotaxi.mapper;

import com.service.datapoa.crud.pontotaxi.dao.jpa.PontoTaxi;
import com.service.datapoa.crud.pontotaxi.dto.PontoTaxiDTO;
import com.service.datapoa.crud.pontotaxi.integration.PontoTaxiRequest;
import com.service.datapoa.crud.pontotaxi.model.PontoTaxiModel;

public class PontoTaxiMapper {
    public static PontoTaxi mapToDAO(PontoTaxiModel model) {
        return new PontoTaxi(model.getName(), model.getLat(), model.getLng(), model.getRegisterTime());
    }

    public static PontoTaxiModel mapToModel(PontoTaxi dao) {
        return new PontoTaxiModel(dao);
    }

    public static PontoTaxiModel mapFromRequest(PontoTaxiRequest request) {
        return new PontoTaxiModel(request.getName(), request.getLat(), request.getLng());
    }

    public static PontoTaxiDTO mapToDTO(PontoTaxiModel model) {
        return new PontoTaxiDTO(model);
    }
}
