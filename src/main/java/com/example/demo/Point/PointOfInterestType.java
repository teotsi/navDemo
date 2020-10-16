package com.example.demo.Point;

public enum PointOfInterestType {
    GATE,
    STORE,
    RESTAURANT,
    SERVICE,
    TOILETS;

    public static PointOfInterestType valueOfByName(String name){
        return valueOf(name.toUpperCase());
    }
}
