package com.example.demo.Router;

import com.example.demo.utilities.FileType;
import com.example.demo.utilities.utilities;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@AllArgsConstructor
public class Router {
    private final static Logger LOGGER = LoggerFactory.getLogger(Router.class);
    private GraphHopper graphHopper;
    private final String mapName;

    public static GraphHopper getGraphHopperInstance(String mapName) throws IOException {
        GraphHopper graphHopper = new GraphHopperOSM().forServer()
                .setDataReaderFile(utilities.getMapResourcePath(mapName, FileType.OSM))
                .setMinNetworkSize(0, 0)
                .setGraphHopperLocation("graphFolder")
                .setEncodingManager(EncodingManager.create("foot"));
        graphHopper.clean();
        graphHopper.importOrLoad();
        LOGGER.info("Graphhopper is ready!");
        return graphHopper;
    }

    public GHResponse getRoute(GHRequest request) {
        return graphHopper.route(request);
    }

    public void refresh() throws IOException {
        this.graphHopper = getGraphHopperInstance(mapName);
    }
}
