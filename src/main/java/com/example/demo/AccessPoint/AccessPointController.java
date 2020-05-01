package com.example.demo.AccessPoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccessPointController {

    private final AccessPointService service;

    @GetMapping("/access-point")
    public List<AccessPoint> getAccessPoints() {
        return service.getAccessPoints();
    }

    @GetMapping("/access-point/{ssid}")
    public AccessPoint getAccessPoint(@PathVariable String ssid) {
        return service.getAccessPoint(ssid);
    }

    @PostMapping("/access-point")
    public void registerAccessPoint(@RequestBody AccessPoint accessPoint) {
        service.registerAccessPoint(accessPoint);
    }
}
