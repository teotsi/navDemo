package com.example.demo.AccessPoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public @Data
class AccessPoint {

    @Id
    private String ssid; // the name of the network that the access point is connected to
    private String bssid; // the mac address of each access point
    private int level;
    private double h; // a constant variable that goes from to 2 to 6 and depends by the environment
    @OneToOne(cascade = {CascadeType.ALL})
    private Position position;
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccessPoint)
            return this.ssid.equals(((AccessPoint) (obj)).ssid);
        else if (obj instanceof String) {
            return this.ssid.equals(obj);
        } else return false;
    }
}
