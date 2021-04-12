package com.example.demo.Weighting;

import com.example.demo.CustomGraphHopper;
import com.example.demo.Point.PointOfInterest;
import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.HintsMap;
import com.graphhopper.routing.weighting.FastestWeighting;
import com.graphhopper.storage.index.LocationIndex;
import com.graphhopper.storage.index.QueryResult;
import com.graphhopper.util.EdgeExplorer;
import com.graphhopper.util.EdgeIterator;
import com.graphhopper.util.EdgeIteratorState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SafeRouteWeighting extends FastestWeighting {
    private static ArrayList<PointOfInterest> restrictedPoints;
    private static Set<Integer> restrictedIds;
    private static CustomGraphHopper graphHopper;

    public SafeRouteWeighting(FlagEncoder encoder, HintsMap hintsMap, ArrayList<PointOfInterest> restrictedIds, CustomGraphHopper graphHopper) {
        super(encoder, hintsMap);
        SafeRouteWeighting.restrictedPoints = restrictedIds;
        SafeRouteWeighting.restrictedIds = new HashSet<Integer>();
        SafeRouteWeighting.graphHopper = graphHopper;
    }


    @Override
    public double calcWeight(EdgeIteratorState edgeState, boolean reverse, int prevOrNextEdgeId) {
        double w = super.calcWeight(edgeState, reverse, prevOrNextEdgeId);
        System.out.println(restrictedIds);
        if (restrictedIds.contains(edgeState.getEdge())) {
            return w * 1000;
        } else {
            return w;
        }
    }
    public static void setRestrictedPoints(ArrayList<PointOfInterest> restrictedPoints) {
        SafeRouteWeighting.restrictedPoints = restrictedPoints;
        LocationIndex index = graphHopper.getLocationIndex();
        EdgeExplorer explorer = graphHopper.getGraphHopperStorage().createEdgeExplorer();
        for (PointOfInterest restrictedPoint: restrictedPoints) {
            QueryResult qr = index.findClosest(restrictedPoint.getLat(), restrictedPoint.getLon(), EdgeFilter.ALL_EDGES);
            EdgeIterator iter = explorer.setBaseNode(qr.getClosestNode());
            while (iter.next()) {
                restrictedIds.add(iter.getEdge());
            }
        }
    }
}
