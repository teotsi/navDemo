package com.example.demo.Point;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    @JsonSerialize
    @Id
    String name;

    @JsonSerialize
    private double lat, lon;
}
