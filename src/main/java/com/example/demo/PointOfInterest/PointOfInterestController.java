package com.example.demo.PointOfInterest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/poi")
public class PointOfInterestController {

    private final PointOfInterestService service;

    @GetMapping("")
    public List<PointOfInterest> getPointsOfInterest(){
        return service.getPointsOfInterest();
    }

    @GetMapping("/{name}")
    public PointOfInterest getPointOfInterest(@PathVariable String name){
        return service.getPointOfInterest(name);
    }

    @PostMapping("")
    public void registerPointOfInterest(@RequestBody PointOfInterest pointOfInterest){
        service.registerPointOfInterest(pointOfInterest);
    }
}
