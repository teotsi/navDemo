package com.example.demo.Nav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NavController {

    @Autowired
    private NavService service;

    @PostMapping("/nav")
    public List<String> getInstructionsFromPoint(@RequestBody Nav coords) {
        return service.getInstructions(coords);
    }

    @GetMapping("/")
    public boolean isUp() {
        return true;
    }
}
