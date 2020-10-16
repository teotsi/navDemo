package com.example.demo.Point;

import com.example.demo.exception.FourOhFourException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PointOfInterestService {

    private final PointOfInterestRepository pointOfInterestRepository;


    public List<PointOfInterest> getPointsOfInterest() {
        return this.pointOfInterestRepository.findAll();
    }

    public PointOfInterest getPointOfInterest(String name) {
        return this.pointOfInterestRepository.findById(name).orElseThrow(FourOhFourException::new);
    }

    public void registerPointOfInterest(PointOfInterest pointOfInterest) {
        pointOfInterestRepository.save(pointOfInterest);
    }

    private void registerPointsOfInterest(List<PointOfInterest> pointsOfInterest) {
        pointOfInterestRepository.saveAll(pointsOfInterest);
    }

    public void deletePointOfInterest(String name) {
        try {
            this.pointOfInterestRepository.deleteById(name);
        } catch (EmptyResultDataAccessException ignored) {
            
        }
    }
}
