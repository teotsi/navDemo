package com.example.demo.AccessPoint;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Position implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private static final double REFERENCE_LAT = 37.99374043917284;
    private static final double REFERENCE_LON = 23.731972208672435;
    private static final double R = 6371e3;
    private double lat;
    private double lon;
    private double x;
    private double y;

    public Position(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double distance() {
        double f1 = Math.cos(REFERENCE_LAT * Math.PI/180.0);
        double f2 = Math.cos(this.lat * Math.PI/180.0);
        double Df = (this.lat - REFERENCE_LAT) * Math.PI/180.0;
        double Dl = (this.lon - REFERENCE_LON) * Math.PI/180.0;
        double a = powSin(Df/2) + f1 * f2 * powSin(Dl/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return d;
    }
    private double powSin(double df) {
        return  (1 - Math.cos(2*df))/2 ;
    }

    public double bearing(){
        double lat1 = Math.toRadians(REFERENCE_LAT);
        double lon1 = Math.toRadians(REFERENCE_LON);
        double lat2 = Math.toRadians(lat);
        double lon2 = Math.toRadians(lon);

        double dl = lon2-lon1;
        double x = Math.cos(lat2) * Math.sin(dl);
        double y = Math.cos(lat1)*Math.sin(lat2) -
                Math.sin(lat1)*Math.cos(lat2)* Math.cos(dl);
        double theta = Math.atan2(x, y);
        return Math.toDegrees(theta);
    }

    public double getLatFromY() {
        return  REFERENCE_LAT + (180/Math.PI) * (y / 6378137);
    }

    public double getLonFromX() {
        return  REFERENCE_LON + (180/Math.PI) * (x / 6378137)/Math.cos(Math.toRadians(REFERENCE_LAT));
    }

    public double getPositionX() {
        double x =  distance() * Math.sin( bearing() * Math.PI /180);
        this.x = x;
        return x;
    }

    public double getPositionY() {
        double distance = distance();
        double bearing = bearing();
        double y = distance * Math.cos( bearing * Math.PI /180);
        this.y = y;
        return y;
    }


}
