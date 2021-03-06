package com.example.demo.CustomDeserializer;

import com.example.demo.Point.PointOfInterest;
import com.example.demo.Point.PointOfInterestType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class PointOfInterestDeserializer extends JsonDeserializer<PointOfInterest> {
    @Override
    public PointOfInterest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String name, description, icon;
        description = icon = "";
        double lon, lat;
        PointOfInterestType amenity;
        boolean restricted;
        JsonNode node = jsonParser.readValueAsTree();

        if (node.has("name")) {
            name = node.get("name").asText();
            lon = node.get("lon").asDouble();
            lat = node.get("lat").asDouble();
            amenity = PointOfInterestType.valueOfByName(node.get("amenity").asText());
            JsonNode restrictedNode = node.get("restricted");
            restricted = restrictedNode != null && restrictedNode.asBoolean();
            JsonNode descriptionNode = node.get("description");
            JsonNode iconNode = node.get("icon");
            description = descriptionNode != null ? descriptionNode.asText() : "";
            icon = iconNode != null ? iconNode.asText() : "";
        } else {
            JsonNode properties = node.get("properties");
            name = properties.get("name").asText();
            amenity = PointOfInterestType.valueOfByName(properties.get("amenity").asText());
            JsonNode coordinates = node.get("geometry").get("coordinates");
            lon = coordinates.get(0).asDouble();
            lat = coordinates.get(1).asDouble();
            JsonNode restrictedNode = properties.get("restricted");
            restricted = restrictedNode != null && restrictedNode.asBoolean();
            JsonNode descriptionNode = properties.get("description");
            JsonNode iconNode = properties.get("icon");
            description = descriptionNode != null ? descriptionNode.asText() : "";
            icon = iconNode != null ? iconNode.asText() : "";
        }
        return new PointOfInterest(name, lat, lon, amenity, restricted, description, icon);
    }
}
