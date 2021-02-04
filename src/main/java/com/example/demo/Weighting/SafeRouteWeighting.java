package com.example.demo.Weighting;

import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.HintsMap;
import com.graphhopper.routing.weighting.FastestWeighting;
import com.graphhopper.util.EdgeIteratorState;

import java.util.ArrayList;

public class SafeRouteWeighting extends FastestWeighting {
    private final ArrayList<Integer> restrictedIds;

    public SafeRouteWeighting(FlagEncoder encoder, HintsMap hintsMap, ArrayList<Integer> restrictedIds) {
        super(encoder, hintsMap);
        this.restrictedIds = restrictedIds;
    }

    @Override
    public double calcWeight(EdgeIteratorState edgeState, boolean reverse, int prevOrNextEdgeId) {
        double w = super.calcWeight(edgeState, reverse, prevOrNextEdgeId);

        if (restrictedIds.contains(edgeState.getEdge())) {
            return w * 1000;
        } else {
            return w;
        }
    }
}
