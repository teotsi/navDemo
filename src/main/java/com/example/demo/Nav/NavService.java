package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class NavService {

    private GraphHopper gh;

    @Value("${map.name}")
    private String mapName;

    @PostConstruct
    private void initializeService() throws IOException {
        gh = new GraphHopperOSM().forServer();
        gh.setDataReaderFile(new ClassPathResource(
                "maps/" + mapName + ".osm").getURI().getPath()); //setting map file
        gh.setMinNetworkSize(0, 0);
// where to store graphhopper files?
        gh.setGraphHopperLocation("graphFolder");
        gh.clean();
        gh.setEncodingManager(EncodingManager.create("foot"));
        gh.importOrLoad();

        System.out.println("Graphhopper is ready!");
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
        InstructionList instructionList = path.getInstructions();
        PointList points = path.getPoints();
        return new Instruction(distance, timeInMs, instructionList, points);
    }
}
