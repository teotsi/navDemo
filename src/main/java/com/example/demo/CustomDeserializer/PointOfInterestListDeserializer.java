package com.example.demo.CustomDeserializer;

import com.example.demo.Point.PointOfInterest;
import com.example.demo.Weighting.SafeRouteWeighting;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PointOfInterestListDeserializer extends JsonDeserializer<List<PointOfInterest>> {
    @Override
    public List<PointOfInterest> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(PointOfInterest.class, new PointOfInterestDeserializer());
        mapper.registerModule(module);
        JsonNode node = jsonParser.readValueAsTree();
        Iterator<JsonNode> it = node.elements();
        List<PointOfInterest> pointOfInterestList = new ArrayList<>();
        ArrayList<PointOfInterest> restrictedPoints = new ArrayList<PointOfInterest>();
        while (it.hasNext()) {
            JsonNode feature = it.next();
            JsonNode featureProperties = feature.get("properties");
            if (featureProperties.has("poi")) {
                PointOfInterest poi = mapper.treeToValue(feature, PointOfInterest.class);
                pointOfInterestList.add(poi);
                if (poi.isRestricted()){
                    restrictedPoints.add(poi);
                }
            }
        }
        SafeRouteWeighting.setRestrictedPoints(restrictedPoints);
        return pointOfInterestList;
    }
}
