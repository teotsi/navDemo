package com.example.demo.AccessPoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public @Data class AccessPoint {

    @Id
    private String ssid; // the name of the network that the access point is connected to
    private String bssid; // the mac address of each access point
    private int level;
    private double h; // a constant variable that goes from to 2 to 6 and depends by the environment
    private double x; // the position of the access point in the x access
    private double y; // the position of the access point in the y access

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccessPoint)
            return this.ssid.equals(((AccessPoint) (obj)).ssid);
        else if (obj instanceof String){
            return this.ssid.equals(obj);
        }
        else return false;
    }
}
