package com.example.demo.utilities;

public enum FileType {
    GEOJSON,
    OSM;

    @Override
    public String toString() {
        return "." + super.toString().toLowerCase();
    }
}


