package com.service.datapoa.crud.linhaonibus.rest;

import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/linhasOnibus")
public class LinhaOnibusController {

    @Autowired
    private LinhaOnibusService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LinhaOnibusModel> linhas() {
        final List<LinhaOnibusModel> linhas = this.service.findAll();

        return linhas;
    }
}
