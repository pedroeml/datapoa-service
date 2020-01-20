package com.service.datapoa.crud.itinerario.mapper;

import com.service.datapoa.crud.itinerario.dao.jpa.Itinerario;
import com.service.datapoa.crud.itinerario.dto.ItinerarioDTO;
import com.service.datapoa.crud.itinerario.integration.ItinerarioRequest;
import com.service.datapoa.crud.itinerario.model.CoordenadaModel;
import com.service.datapoa.crud.itinerario.model.ItinerarioModel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ItinerarioMapper {
    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static List<Itinerario> mapToDAO(ItinerarioModel model) {
        return model.getCoordenadas().parallelStream()
            .map(coordenada -> new Itinerario(model.getId(), coordenada.getLat(), coordenada.getLng()))
            .collect(Collectors.toList());
    }

    public static ItinerarioModel mapToModel(List<Itinerario> itinerario) {
        final int idLinha = itinerario.get(0).getIdLinha();
        List<CoordenadaModel> coordenadas = itinerario.parallelStream()
                .map(it -> new CoordenadaModel(it.getLat(), it.getLng()))
                .collect(Collectors.toList());
        return new ItinerarioModel(idLinha, coordenadas);
    }

    public static ItinerarioModel mapFromUntypedResponse(Map<String, Object> untypedResponseBody) {
        final String idlinha = (String) untypedResponseBody.get("idlinha");
        final List<CoordenadaModel> coordenadas = new LinkedList<>();

        for (Map.Entry<String, Object> entry : untypedResponseBody.entrySet()) {
            if (!isNumber(entry.getKey())) {
                continue;
            }
            final Map<String, String> value = (LinkedHashMap<String, String>) entry.getValue();
            coordenadas.add(new CoordenadaModel(value.get("lat"), value.get("lng")));
        }

        return new ItinerarioModel(Integer.parseInt(idlinha), coordenadas);
    }

    public static ItinerarioModel mapFromRequest(ItinerarioRequest request) {
        final List<CoordenadaModel> coordenadas = request.getCoordenadas().parallelStream()
            .map(coordenada -> new CoordenadaModel(coordenada.getLat(), coordenada.getLng()))
            .collect(Collectors.toList());
        return new ItinerarioModel(request.getId(), coordenadas);
    }

    public static ItinerarioDTO mapToDTO(ItinerarioModel model) {
        return new ItinerarioDTO(model);
    }
}
