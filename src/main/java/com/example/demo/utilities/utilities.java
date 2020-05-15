package com.example.demo.utilities;

import com.graphhopper.util.shapes.GHPoint3D;

public final class utilities {
    public static String getAngle(GHPoint3D srcPoint, GHPoint3D destPoint) {
        double adjustedLat = srcPoint.getLat() - destPoint.getLat();
        double adjustedLon = srcPoint.getLon() - destPoint.getLon();

        String LatDirection;
        String LonDirection;

        LatDirection = adjustedLat > 0 ? "south" : "north";
        LonDirection = adjustedLon > 0 ? "west" : "east";

        return LatDirection + "-" + LonDirection;
    }
}
