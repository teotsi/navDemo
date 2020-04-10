package com.example.demo.AccessPoint;

public class AccessPoint {
    private String ssid; // the name of the network that the access point is connected to
    private String bSsid; // the mac address of each access point
    private int level;
    private double h; // a constant variable that goes from to 2 to 6 and depends by the environment
    private double x; // the position of the access point in the x access
    private double y; // the position of the access point in the y access

    public AccessPoint() {
    }

    public AccessPoint(String ssid, String bSsid, int level, double h, double x, double y) {
        this.ssid = ssid;
        this.bSsid = bSsid;
        this.level = level;
        this.h = h;
        this.x = x;
        this.y = y;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getbSsid() {
        return bSsid;
    }

    public void setbSsid(String bSsid) {
        this.bSsid = bSsid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
