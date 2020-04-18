package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.storage.index.LocationIndex;
import com.graphhopper.util.Helper;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class NavService {

    private GraphHopper gh;
    private LocationIndex index;
    private double victoriaLatitude, victoriaLongitude, auebLatitude, auebLongitude;

    public NavService() {

        gh = new GraphHopperOSM().forServer();
        gh.setDataReaderFile("maps/asoee.osm");
// where to store graphhopper files?
        gh.setGraphHopperLocation("graphFolder");
        gh.clean();
        gh.setEncodingManager(EncodingManager.create("foot"));
        gh.importOrLoad();
        index = gh.getLocationIndex();
        String jsonStr = null;
        try {
            jsonStr = Helper.isToString(new FileInputStream(new File("maps/asoee.geojson")));
            JSONObject json = new JSONObject(jsonStr);
            JSONArray entries = json.getJSONArray("features");

            victoriaLatitude = victoriaLongitude = auebLatitude = auebLongitude = 0; //this is updated in the for loop
            for (int objectCnt = 0; objectCnt < entries.length(); objectCnt++) {
                JSONObject entry = entries.getJSONObject(objectCnt);
                JSONObject geo = entry.getJSONObject("geometry");
//                JSONObject features = entry.getJSONObject("features");
                JSONArray coords = geo.getJSONArray("coordinates");
                String type = entry.getString("type");
                if (type.equals("Feature")) {
                    try {

                        String name = entry.getJSONObject("properties").getString("name:en");
//                    System.out.println(name);
                        if (name.equals("Victoria")) {
                            victoriaLatitude = coords.getDouble(1);
                            victoriaLongitude = coords.getDouble(0);
                        } else if (name.contains("Athens University of Economics")) {
                            coords = (JSONArray) coords.getJSONArray(0).get(5);
                            auebLatitude = coords.getDouble(1);
                            auebLongitude = coords.getDouble(0);
                        }
                    } catch (Exception e) {
                        continue;
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("so the reader commences");
    }
    public com.example.demo.Instruction.Instruction getInstructions(Nav coords) {

//        QueryResult qr = index.findClosest(calculatedLat,calculatedLon, EdgeFilter.ALL_EDGES );

//        QueryResult qr = index.findClosest(coords.getLat(), coords.getLon(), EdgeFilter.ALL_EDGES );
//        EdgeIteratorState edge = qr.getClosestEdge();
//        System.out.println(qr.getSnappedPoint());
        GHRequest req = new GHRequest(coords.getSrcLat(), coords.getSrcLon(), coords.getDestLat(), coords.getDestLon()).
                setWeighting("fastest").
                setVehicle("foot").
                setLocale(Locale.US);
        GHResponse rsp = gh.route(req);

        if (rsp.hasErrors()) {
            // handle them!
            for (Throwable error : rsp.getErrors()) {
//                System.out.println(error.getMessage());
            }
        }

// use the best path, see the GHResponse class for more possibilities.
        PathWrapper path = rsp.getBest();

// distance in meters and time in millis of the full path
        double distance = Math.round(path.getDistance());
        long timeInMs = path.getTime();
        System.out.println(path.getDescription());
        InstructionList instructionList = path.getInstructions();
        PointList points = path.getPoints();
        return new Instruction(distance,timeInMs,instructionList,points);
    }
}
