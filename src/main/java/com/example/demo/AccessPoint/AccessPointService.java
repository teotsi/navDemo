package com.example.demo.AccessPoint;

import com.example.demo.exception.FourOhFourException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccessPointService {

    @Autowired
    private AccessPointRepository accessPointRepository;

    private List<AccessPoint> accessPoints = new ArrayList<>(Arrays.asList(new AccessPoint("pi1", "pi1-b",
            2, 3.14, 2.4, 5.3), new AccessPoint("pi2", "pi2-b",
            2, 3.14, 5.63, 5.33), new AccessPoint("pi3", "pi3-b",
            2, 3.14, 1.6, 10.2), new AccessPoint("pi4", "pi4-b",
            2, 3.14, 5.3, 7.3)));

    public List<AccessPoint> getAccessPoints() {
        return this.accessPointRepository.findAll();
    }

    public AccessPoint getAccessPoint(String ssid) {
        return this.accessPointRepository.findById(ssid).orElseThrow(FourOhFourException::new);


    }

    public void registerAccessPoint(AccessPoint accessPoint) {
        accessPointRepository.save(accessPoint);
    }
}

