package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NavController {


    private final NavService service;

    @PostMapping("/nav")
    public Instruction getInstructionsFromPoint(@RequestBody Nav coords) {
        return service.getInstructions(coords);
    }

}
