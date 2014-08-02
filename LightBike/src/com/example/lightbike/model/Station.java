package com.example.lightbike.model;

/**
 * Created by sim on 8/2/14.
 */
public class Station {
    public final long id;
    public final double latitude;
    public final double longitude;
    public String name;
    public int bikeNum;
    public String locationDesc;

    public Station(long id, double latitude, double longitude, String name, int bikeNum, String desc) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;

        this.name = name;
        this.bikeNum = bikeNum;
        this.locationDesc = desc;
    }
}
