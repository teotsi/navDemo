package com.example.demo.Nav;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Nav {

    private double srcLat, srcLon, destLat, destLon;
    //private double picLat, picLon;

//    public void getDirection(double dirLat, double dirLon) {
//        double adjustedDirLat = dirLat - srcLat;
//        double adjustedDirLon = dirLon - srcLon;
//        String LatDirection;
//        String LonDirection;
//        String DirLatDirection;
//        String DirLonDirection;
//        if (picLon > 0) {
//            LonDirection = "east";
//        } else {
//            LonDirection = "west";
//        }
//        if (picLat > 0) {
//            LatDirection = "north";
//        } else {
//            LatDirection = "south";
//        }
//        if (dirLon - srcLon >= 0) {
//            DirLonDirection = "east";
//        } else {
//            DirLonDirection = "west";
//        }
//        if (dirLat - srcLat >= 0) {
//            DirLatDirection = "north";
//        } else {
//            DirLatDirection = "south";
//        }
//        System.out.println("Looking towards "+(picLon+ srcLon)+", "+(picLat+ srcLat)+"\nShould look at "+dirLon+", "+dirLat+"\nSo, turn "+getAngleAlt(adjustedDirLon,adjustedDirLat));
//        System.out.println("-------------------------------------");
//    }

    public double getAngle(double dirLon, double dirLat, double picLat, double picLon) {
        double dotProduct = dotProduct(picLon, picLat,dirLon,dirLat);
        double aMagnitude = magnitude(picLon,picLat);
        double bMagnitude = magnitude(dirLon,dirLat);
        return Math.toDegrees(dotProduct/(aMagnitude*bMagnitude));
    }

    private double dotProduct(double ax, double ay, double bx,double by){
        return (ax*bx)+(ay*by);
    }

    private double magnitude(double x, double y){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
//    private double getAngleAlt(double x, double y){
//        double angle =  Math.toDegrees(Math.atan2(y - picLat, x - picLon));
//
////        if(angle < 0){
////            angle += 360;
////        }
//
//        return angle;
//    }
}

