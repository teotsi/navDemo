package com.example.demo.AccessPoint;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Service
public class AccessPointService {
    private List<AccessPoint> accessPoints = Arrays.asList(new AccessPoint("pi1", "pi1-b",
            2, 3.14, 2.4, 5.3), new AccessPoint("pi2", "pi2-b",
            2, 3.14, 5.63, 5.33), new AccessPoint("pi3", "pi3-b",
            2, 3.14, 1.6, 10.2), new AccessPoint("pi4", "pi4-b",
            2, 3.14, 5.3, 7.3));

    public List<AccessPoint> getAccessPoints() {
        return this.accessPoints;
    }

    public AccessPoint getAccessPoint( String ssid) {
        return accessPoints.stream().filter(accessPoint -> accessPoint.getSsid()
                .equals(ssid)).findFirst().get();
    }

    public void registerAccessPoint(AccessPoint accessPoint) {
        accessPoints.add(accessPoint);
    }
}

