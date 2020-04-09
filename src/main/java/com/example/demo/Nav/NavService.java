package com.example.demo.Nav;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.storage.index.LocationIndex;
import com.graphhopper.storage.index.QueryResult;
import com.graphhopper.util.EdgeIteratorState;
import com.graphhopper.util.Helper;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        index= gh.getLocationIndex();
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

    public List<String> getInstructions(Nav coords) {

//        QueryResult qr = index.findClosest(coords.getLat(), coords.getLon(), EdgeFilter.ALL_EDGES );
//        EdgeIteratorState edge = qr.getClosestEdge();
//        System.out.println(qr.getSnappedPoint());
        GHRequest req = new GHRequest(coords.getLat(),coords.getLon(), auebLatitude, auebLongitude).
                setWeighting("fastest").
                setVehicle("foot").
                setLocale(Locale.US);
        GHResponse rsp = gh.route(req);

        if (rsp.hasErrors()) {
            // handle them!
            for (Throwable error : rsp.getErrors()) {
                System.out.println(error.getMessage());
            }
        }

// use the best path, see the GHResponse class for more possibilities.
        PathWrapper path = rsp.getBest();

// distance in meters and time in millis of the full path
        double distance = path.getDistance();
        long timeInMs = path.getTime();

        InstructionList il = path.getInstructions();
// iterate over every turn instruction
        String dist = "Distance: " + Math.round(distance) + " meters";
        String details  = "Estimated time: " + (timeInMs / 60000) + " minutes";
        ArrayList<String> instructions = new ArrayList<String>();
        instructions.add(dist);
        instructions.add(details);
        for (Instruction instruction : il) {
            instructions.add(mapInstruction(instruction.getSign()) + " for " + Math.round(instruction.getDistance()) + " meters, on " + instruction.getName());
        }
        return instructions;
    }

    private static String mapInstruction(int code) {
        if (code == -3) {
            return "Turn sharp left";
        } else if (code == -2) {
            return "Turn left";
        } else if (code == -1) {
            return "Turn slight left";
        } else if (code == 0) {
            return "Straight";
        } else if (code == 1) {
            return "Turn slight right";
        } else if (code == 2) {
            return "Turn right";
        } else if (code == 3) {
            return "Turn sharp right";
        } else if (code == 4) {
            return "Tadaaa!";
        } else if (code == 5) {
            return "Via reached";
        } else if (code == 6) {
            return "use roundabout!";
        } else {
            return "keep right";
        }
    }
}
