package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NavController {

    @Autowired
    private NavService service;

    @PostMapping("/nav")
    public Instruction getInstructionsFromPoint(@RequestBody Nav coords) {
        return service.getInstructions(coords);
    }

}
