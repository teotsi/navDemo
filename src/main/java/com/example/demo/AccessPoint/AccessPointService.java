package com.example.demo.AccessPoint;

import com.example.demo.exception.FourOhFourException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessPointService {

    @Autowired
    private AccessPointRepository accessPointRepository;

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

