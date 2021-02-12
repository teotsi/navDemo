package com.example.demo.Point;

import com.example.demo.CustomDeserializer.PointOfInterestDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    public PointOfInterest(String name, double lat, double lon, PointOfInterestType amenity) {
        super(name, lat, lon);
        this.amenity = amenity;
        this.restricted = false;
    }

    public PointOfInterest(String name, double lat, double lon, PointOfInterestType amenity, boolean restricted) {
        super(name, lat, lon);
        this.amenity = amenity;
        this.restricted = restricted;
    }

    @Override
    public String toString(){
        return "PointOfInterest: amenity=" + amenity + ", name="+ this.name+", restricted="+ this.restricted;
    }

}
