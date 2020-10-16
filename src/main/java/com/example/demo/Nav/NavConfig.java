package com.example.demo.Nav;

import com.example.demo.utilities.FileType;
import com.example.demo.utilities.utilities;
import com.graphhopper.GraphHopper;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.EncodingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NavConfig {

    @Value("${map.name}")
    private String mapName;

    private final Logger LOGGER = LoggerFactory.getLogger(NavConfig.class);
    @Bean
    GraphHopper initializeGraphHopperService() throws IOException {
        GraphHopper gh1 = new GraphHopperOSM().forServer()
                .setDataReaderFile(utilities.getMapResourcePath(mapName, FileType.OSM))
                .setMinNetworkSize(0, 0)
                .setGraphHopperLocation("graphFolder")
                .setEncodingManager(EncodingManager.create("foot"));

        gh1.clean();
        gh1.importOrLoad();
        LOGGER.info("Graphhopper is ready!");
        return gh1;
    }

}
