package com.example.demo.PointOfInterest;

import com.example.demo.CustomDeserializer.PointOfInterestDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize(using = PointOfInterestDeserializer.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  PointOfInterest {

    @Id
    private String name;
    private double lat, lon;

}
