package com.example.demo.Point;

import com.example.demo.CustomDeserializer.PointOfInterestDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize(using = PointOfInterestDeserializer.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PointOfInterest extends Point {

    private PointOfInterestType amenity;
    private boolean restricted;
    private String description;
    private String icon;

    public PointOfInterest(String name, double lat, double lon, PointOfInterestType amenity, String description, String icon) {
        super(name, lat, lon);
        this.amenity = amenity;
        this.restricted = false;
        this.description = description;
        this.icon = icon;
    }

    public PointOfInterest(String name, double lat, double lon, PointOfInterestType amenity, boolean restricted, String description, String icon) {
        super(name, lat, lon);
        this.amenity = amenity;
        this.restricted = restricted;
        this.description = description;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "PointOfInterest: amenity=" + amenity + ", name=" + this.name + ", restricted=" + this.restricted;
    }

}
