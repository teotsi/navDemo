package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import com.example.demo.utilities.FileType;
import com.example.demo.utilities.utilities;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Locale;

@Service
public class NavService {

    private GraphHopper gh;

    @Value("${map.name}")
    private String mapName;

    @PostConstruct
    private void initializeService() throws IOException {
        gh = new GraphHopperOSM().forServer()
                .setDataReaderFile(utilities.getMapResourcePath(mapName, FileType.OSM))
                .setMinNetworkSize(0, 0)
                .setGraphHopperLocation("graphFolder")
                .setEncodingManager(EncodingManager.create("foot"));

        gh.clean();
        gh.importOrLoad();
        System.out.println("Graphhopper is ready!");
    }

    public com.example.demo.Instruction.Instruction getInstructions(Nav coords) {
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
