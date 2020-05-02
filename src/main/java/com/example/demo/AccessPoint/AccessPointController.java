package com.example.demo.AccessPoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/access-point")
public class AccessPointController {

    private final AccessPointService service;

    @GetMapping("")
    public List<AccessPoint> getAccessPoints() {
        return service.getAccessPoints();
    }

    @GetMapping("/{ssid}")
    public AccessPoint getAccessPoint(@PathVariable String ssid) {
        return service.getAccessPoint(ssid);
    }

    @PostMapping("")
    public void registerAccessPoint(@RequestBody AccessPoint accessPoint) {
        service.registerAccessPoint(accessPoint);
    }
}
