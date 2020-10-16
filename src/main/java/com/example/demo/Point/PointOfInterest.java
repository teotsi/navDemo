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
@ToString
@JsonDeserialize(using = PointOfInterestDeserializer.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PointOfInterest extends Point {

    private PointOfInterestType amenity;

    public PointOfInterest(String name, double lat, double lon, PointOfInterestType amenity) {
        super(name, lat, lon);
        this.amenity = amenity;
    }


}
