package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import com.example.demo.Router.Router;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.PathWrapper;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class NavService {

    private Router router;

    public com.example.demo.Instruction.Instruction getInstructions(Nav coords) {
        GHRequest req = new GHRequest(coords.getSrcLat(), coords.getSrcLon(), coords.getDestLat(), coords.getDestLon()).
                setWeighting("fastest").
                setVehicle("foot").
                setLocale(Locale.US);
        GHResponse rsp = router.getRoute(req);

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
