package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/nav")
public class NavController {

    private final NavService service;

    @PostMapping("")
    public Instruction getInstructionsFromPoint(@RequestBody Nav coords) {
         return service.getInstructions(coords);
    }
}
