package com.example.demo.Point;

public enum PointOfInterestType {
    CLASSROOM,
    CSLAB,
    GATE,
    HALL,
    LIBRARY,
    OFFICE,
    RESTAURANT,
    SERVICE,
    STORE,
    TOILETS,
    ;

    public static PointOfInterestType valueOfByName(String name) {
        return valueOf(name.toUpperCase());
    }
}
