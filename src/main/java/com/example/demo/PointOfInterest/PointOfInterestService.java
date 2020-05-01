package com.example.demo.PointOfInterest;

import com.example.demo.CustomDeserializer.PointOfInterestListDeserializer;
import com.example.demo.exception.FourOhFourException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointOfInterestService {

    private final PointOfInterestRepository pointOfInterestRepository;
    @Value("${map.name}")
    private String mapName;

    @PostConstruct
    public void parseGeoJson() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new PointOfInterestListDeserializer());
        mapper.registerModule(module);
        try {
            InputStream resource = new ClassPathResource("maps/" + mapName + ".geojson").getInputStream();
            JsonNode features = mapper.readTree(resource).get("features");

            List<PointOfInterest> pointsOfInterest = mapper.readValue(features.toString(),
                    new TypeReference<>() {
                    });
            this.registerPointsOfInterest(pointsOfInterest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PointOfInterest> getPointsOfInterest() {
        return this.pointOfInterestRepository.findAll();
    }

    public PointOfInterest getPointOfInterest(String name) {
        return this.pointOfInterestRepository.findById(name).orElseThrow(FourOhFourException::new);
    }

    public void registerPointOfInterest(PointOfInterest pointOfInterest) {
        pointOfInterestRepository.save(pointOfInterest);
    }

    private void registerPointsOfInterest(List<PointOfInterest> pointsOfInterest) {
        pointOfInterestRepository.saveAll(pointsOfInterest);
    }
}
