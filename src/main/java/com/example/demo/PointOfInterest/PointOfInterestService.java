package com.example.demo.PointOfInterest;

import com.example.demo.exception.FourOhFourException;
import com.graphhopper.util.Helper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PointOfInterestService {

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;
    @Value("${map.name}")
    private String mapName;


    @PostConstruct
    private void readGeoJson() {
        try {
            String jsonStr = Helper.isToString(new FileInputStream(new File("maps/" + mapName + ".geojson")));
            JSONObject json = new JSONObject(jsonStr);
            JSONArray entries = json.getJSONArray("features");
            for (Object element : entries) {
                try {
                    JSONObject entry = (JSONObject) element;
                    JSONObject properties = entry.getJSONObject("properties");
                    String pointOfInterest = properties.getString("poi");
                    if (pointOfInterest.equals("yes")) {
                        JSONArray coordinates = entry.getJSONObject("geometry").getJSONArray("coordinates");
                        String name = entry.getJSONObject("properties").getString("name");
                        PointOfInterest poi = new PointOfInterest(name, coordinates.getDouble(1),
                                coordinates.getDouble(0));
                        this.registerPointOfInterest(poi);
                    }
                } catch (Exception e) {
                    if (!(e instanceof JSONException)) {
                        e.printStackTrace();
                    }
                }

            }
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
}
