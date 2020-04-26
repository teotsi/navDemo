package com.example.demo.PointOfInterest;

import com.example.demo.AccessPoint.AccessPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PointOfInterestController {

    @Autowired
    private PointOfInterestService service;

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
