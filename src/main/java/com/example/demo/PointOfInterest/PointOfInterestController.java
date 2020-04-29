package com.example.demo.PointOfInterest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PointOfInterestController {

    private final PointOfInterestService service;

    @GetMapping("/poi")
    public List<PointOfInterest> getPointsOfInterest(){
        return service.getPointsOfInterest();
    }

    @GetMapping("/poi/{name}")
    public PointOfInterest getPointOfInterest(@PathVariable String name){
        return service.getPointOfInterest(name);
    }

    @PostMapping("/poi")
    public void registerPointOfInterest(@RequestBody PointOfInterest pointOfInterest){
        service.registerPointOfInterest(pointOfInterest);
    }
}
