package com.example.demo.Point;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PointOfReference extends Point {
    private double height;

    public PointOfReference(String name, double lat, double lon, double height) {
        super(name, lat, lon);
        this.height = height;
    }
}
