package com.service.datapoa.crud.linhaonibus.mapper;

import com.service.datapoa.crud.linhaonibus.dto.LinhaOnibusDTO;
import com.service.datapoa.crud.linhaonibus.integration.LinhaOnibusResponse;
import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;

public class LinhaOnibusMapper {
    public static LinhaOnibusModel mapToModel(LinhaOnibusResponse response) {
        return new LinhaOnibusModel(response);
    }

    public static LinhaOnibusDTO mapToDTO(LinhaOnibusModel model) {
        return new LinhaOnibusDTO(model);
    }
}
