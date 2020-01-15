package com.service.datapoa.crud.linhaonibus.mapper;

import com.service.datapoa.crud.linhaonibus.dao.jpa.LinhaOnibus;
import com.service.datapoa.crud.linhaonibus.dto.LinhaOnibusDTO;
import com.service.datapoa.crud.linhaonibus.integration.LinhaOnibusRequest;
import com.service.datapoa.crud.linhaonibus.integration.LinhaOnibusResponse;
import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;

public class LinhaOnibusMapper {
    public static LinhaOnibus mapToDAO(LinhaOnibusModel model) {
        return new LinhaOnibus(model.getCodigo(), model.getNome());
    }

    public static LinhaOnibusModel mapToModel(LinhaOnibus dao) {
        return new LinhaOnibusModel(dao);
    }

    public static LinhaOnibusModel mapFromResponse(LinhaOnibusResponse response) {
        return new LinhaOnibusModel(response);
    }

    public static LinhaOnibusModel mapFromRequest(LinhaOnibusRequest request) {
        return new LinhaOnibusModel(request.getCodigo(), request.getNome());
    }

    public static LinhaOnibusDTO mapToDTO(LinhaOnibusModel model) {
        return new LinhaOnibusDTO(model);
    }
}
