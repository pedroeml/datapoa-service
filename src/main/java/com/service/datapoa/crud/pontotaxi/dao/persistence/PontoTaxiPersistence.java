package com.service.datapoa.crud.pontotaxi.dao.persistence;

import com.service.datapoa.crud.pontotaxi.dao.jpa.PontoTaxi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PontoTaxiPersistence {
    private static final String fileName = "pontos-taxi.txt";

    public static List<PontoTaxi> readBackupFile() {
        final LinkedList<PontoTaxi> pontos = new LinkedList<>();
        final Resource resource = new ClassPathResource(fileName);
        try (
            InputStream inputStream = resource.getInputStream();
            Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8);
        ) {
            while (sc.hasNext()) {
                final String[] values = sc.nextLine().split("#");
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

    public static void appendBackupFile(PontoTaxi ponto) {
        final Resource resource = new ClassPathResource(fileName);
        try (FileWriter writer = new FileWriter(resource.getFile(), StandardCharsets.UTF_8)) {
            final String template = "%s#%s#%s#%s\n";
            final String line = String.format(template, ponto.getName(), ponto.getLat(), ponto.getLng(), ponto.getRegisterTime());
            writer.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
