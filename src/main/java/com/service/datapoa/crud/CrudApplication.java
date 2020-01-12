package com.service.datapoa.crud;

import com.service.datapoa.crud.linhaonibus.model.LinhaOnibusModel;
import com.service.datapoa.crud.linhaonibus.rest.LinhaOnibusService;
import com.service.datapoa.crud.pontotaxi.model.PontoTaxiModel;
import com.service.datapoa.crud.pontotaxi.rest.PontoTaxiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudApplication {

    private static final Logger log = LoggerFactory.getLogger(CrudApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PontoTaxiService pontoTaxiService) {
        return args -> {
            final List<PontoTaxiModel> pontos = pontoTaxiService.findAll();

            log.info("List of PontoTaxi found with findAll()");
            log.info("--------------------------------------");
            pontos.forEach(ponto -> {
                log.info(ponto.toString());
            });
            log.info("");
        };
    }

    @Bean
    public CommandLineRunner run(LinhaOnibusService linhasOnibusService) {
        return args -> {
            final List<LinhaOnibusModel> linhas = linhasOnibusService.findAll();

            log.info("List of LinhaOnibus found through REST");
            log.info("--------------------------------------");
            linhas.forEach(linha -> {
                log.info(linha.toString());
            });
            log.info("");
        };
    }
}
