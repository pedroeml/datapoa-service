package com.service.datapoa.crud.itinerario.mapper;

import com.service.datapoa.crud.itinerario.dto.ItinerarioDTO;
import com.service.datapoa.crud.itinerario.model.CoordenadaModel;
import com.service.datapoa.crud.itinerario.model.ItinerarioModel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ItinerarioMapper {
    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static ItinerarioModel mapFromUntypedResponse(Map<String, Object> untypedResponseBody) {
        final String idlinha = (String) untypedResponseBody.get("idlinha");
        final String codigo = (String) untypedResponseBody.get("codigo");
        final String nome = (String) untypedResponseBody.get("nome");
        final List<CoordenadaModel> coordenadas = new LinkedList<>();

        for (Map.Entry<String, Object> entry : untypedResponseBody.entrySet()) {
            if (!isNumber(entry.getKey())) {
                continue;
            }
            final Map<String, String> value = (LinkedHashMap<String, String>) entry.getValue();
            coordenadas.add(new CoordenadaModel(value.get("lat"), value.get("lng")));
        }

        return new ItinerarioModel(idlinha, codigo, nome, coordenadas);
    }

    public static ItinerarioDTO mapToDTO(ItinerarioModel model) {
        return new ItinerarioDTO(model);
    }
}
