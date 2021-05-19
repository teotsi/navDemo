package com.example.demo.Point;

import com.example.demo.CustomDeserializer.PointOfInterestListDeserializer;
import com.example.demo.utilities.FileType;
import com.example.demo.utilities.utilities;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PoiInitializer implements CommandLineRunner {
    private final PointOfInterestRepository repository;
    @Value("${map.name}")
    private String mapName;

    @Override
    public void run(String... args) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new PointOfInterestListDeserializer());
        mapper.registerModule(module);
        try {
            InputStream resource = utilities.getMapResourceStream(mapName, FileType.GEOJSON);
            JsonNode features = mapper.readTree(resource).get("features");
            List<PointOfInterest> pointsOfInterest = mapper.readValue(features.toString(),
                    new TypeReference<>() {
                    });

            this.repository.saveAll(pointsOfInterest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
