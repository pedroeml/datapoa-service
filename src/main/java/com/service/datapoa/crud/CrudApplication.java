package com.service.datapoa.crud;

import com.service.datapoa.crud.pontotaxi.jpa.PontoTaxi;
import com.service.datapoa.crud.pontotaxi.jpa.PontoTaxiRepository;
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
    public CommandLineRunner demo(PontoTaxiRepository repository) {
        return (args) -> {
            repository.save(new PontoTaxi("PONTO-ZONA-SUL", "-30.12373379817800000", "-51.22266028234100000", "2019-02-10T16:14:34.828"));
            repository.save(new PontoTaxi("PONTO-ZONA-NORTE-1", "-30.0103346", "-51.1724526", "2019-03-10T16:14:34.828"));

            log.info("PontoTaxi found with findAll()");
            log.info("------------------------------");
            for (PontoTaxi ponto : repository.findAll()) {
                log.info(ponto.toString());
            }
            log.info("");

            final PontoTaxi pontoTaxi = repository.findById(1L);
            log.info("PontoTaxi found with findById(1L)");
            log.info("---------------------------------");
            log.info(pontoTaxi.toString());
            log.info("");

            log.info("PontoTaxi found with findByName('PONTO-ZONA-NORTE-1')");
            log.info("-----------------------------------------------------");
            final List<PontoTaxi> pontos = repository.findByName("PONTO-ZONA-NORTE-1");
            pontos.forEach(ponto -> {
                log.info(ponto.toString());
            });
            log.info("");
        };
    }
}
