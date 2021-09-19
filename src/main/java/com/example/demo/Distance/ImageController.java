package com.example.demo.Distance;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final ImageService service;

    @GetMapping("")
    public List<Image> getImages() {
        return service.getImages();
    }

    @GetMapping("/{classId}")
    public Image getImage(@PathVariable String classId) {
        return service.getImage(classId);
    }

    @PostMapping("")
    public void registerImage(@RequestBody Image image) {
        service.registerImage(image);
    }
}
