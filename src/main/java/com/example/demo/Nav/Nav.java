package com.example.demo.Nav;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public @Data
class Nav {
    private double lat, lon, picLat, picLon;

    public double getCalculatedLat() {
        return lat+picLat;
    }

    public double getCalculatedLon(){
        return lon+picLon;
    }
}
