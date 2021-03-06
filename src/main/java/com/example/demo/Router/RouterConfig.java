package com.example.demo.Router;

import com.example.demo.CustomGraphHopper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RouterConfig {

    @Value("${map.name}")
    private String mapName;

    @Bean
    Router initializeGraphHopperService() throws IOException {
        return new Router(createGraphHopper(), mapName);
    }

    private CustomGraphHopper createGraphHopper() throws IOException {
        return Router.getGraphHopperInstance(mapName);
    }
}
