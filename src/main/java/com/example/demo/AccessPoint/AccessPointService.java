package com.example.demo.AccessPoint;

import com.example.demo.exception.FourOhFourException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccessPointService {

    private final AccessPointRepository accessPointRepository;

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

