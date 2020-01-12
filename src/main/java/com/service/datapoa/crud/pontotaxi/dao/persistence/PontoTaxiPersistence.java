package com.service.datapoa.crud.pontotaxi.dao.persistence;

import com.service.datapoa.crud.pontotaxi.dao.PontoTaxi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PontoTaxiPersistence {
    private static final String fileName = "pontos-taxi.txt";

    public static List<PontoTaxi> readBackupFile() {
        LinkedList<PontoTaxi> pontos = new LinkedList<>();
        Resource resource = new ClassPathResource(fileName);
        try (
                InputStream inputStream = resource.getInputStream();
                Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8);
        ) {
            while (sc.hasNext()) {
                String[] values = sc.nextLine().split("#");
                pontos.add(new PontoTaxi(values[0], values[1], values[2], values[3]));
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pontos;
    }
}
