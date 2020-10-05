package com.example.demo.utilities;

import com.graphhopper.util.shapes.GHPoint3D;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

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

    public static ClassPathResource getMapResource(String mapName, FileType mapType) {
        return new ClassPathResource("maps/" + mapName + mapType.toString());
    }

    public static InputStream getMapResourceStream(String mapName, FileType mapType) throws IOException {
        return getMapResource(mapName, mapType).getInputStream();
    }

    public static String getMapResourcePath(String mapName, FileType mapType) throws IOException {
        return getMapResource(mapName, mapType).getURI().getPath();
    }

}
