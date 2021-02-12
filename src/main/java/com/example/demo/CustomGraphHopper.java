package com.example.demo;

import com.example.demo.Point.PointOfInterest;
import com.example.demo.Weighting.SafeRouteWeighting;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.HintsMap;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.Graph;

import java.util.ArrayList;

public class CustomGraphHopper extends GraphHopperOSM {

    @Override
    public Weighting createWeighting( HintsMap hintsMap, FlagEncoder encoder,  Graph graph) {
        return new SafeRouteWeighting(encoder, hintsMap, new ArrayList<PointOfInterest>(), this);
    }
}
