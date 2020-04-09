package com.example.demo.Nav;

public class Nav {


    private double lat,lon;

    public Nav(){

    }
    public Nav(double lat, double lon){
        super();
        this.lat=lat;
        this.lon=lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
