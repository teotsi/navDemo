package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/nav")
public class NavController {


    private final NavService service;

    @PostMapping("")
    public Instruction getInstructionsFromPoint(@RequestBody Nav coords) {
        return service.getInstructions(coords);
    }

}
