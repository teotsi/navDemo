package com.example.demo;

import com.example.demo.AccessPoint.AccessPoint;
import com.example.demo.AccessPoint.AccessPointController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void register() {
        AccessPointController accessPointController = new AccessPointController();
        accessPointController.registerAccessPoint(new AccessPoint("pi1", "pi1b", 5, 3.3, 2.1, 5.3));
        List<AccessPoint> response = accessPointController.getAccessPoints();
        assert (response.isEmpty());
    }
}
