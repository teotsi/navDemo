package com.example.demo.Point;

public enum PointOfInterestType {
    COMMERCIAL,
    UTILITY
    ;

    public static PointOfInterestType valueOfByName(String name) {
        return valueOf(name.toUpperCase());
    }
}
