package com.example.demo.Distance;

import com.example.demo.exception.FourOhFourException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public List<Image> getImages() { return this.imageRepository.findAll();};

    public Image getImage(String classId) {
        return this.imageRepository.findById(classId).orElse(new Image());
    }

    public void registerImage(Image image) {
        imageRepository.save(image);
    }
}
