package com.example.demo.CustomDeserializer;

import com.example.demo.PointOfInterest.PointOfInterest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PointOfInterestDeserializer extends JsonDeserializer<PointOfInterest> {
    @Override
    public PointOfInterest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String name;
        double lon,lat;
        JsonNode node = jsonParser.readValueAsTree();
        if (node.has("name")){
            name = node.get("name").asText();
            lon = node.get("lon").asDouble();
            lat = node.get("lat").asDouble();
        }else {
            JsonNode properties = node.get("properties");
            name = properties.get("name").asText();
            JsonNode coordinates = node.get("geometry").get("coordinates");
            lon = coordinates.get(0).asDouble();
            lat = coordinates.get(1).asDouble();
        }

        return new PointOfInterest(name, lat, lon);
    }
}
