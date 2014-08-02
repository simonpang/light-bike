package com.example.lightbike.model;

/**
 * Created by sim on 8/2/14.
 */
public class Station {
    public final long id;
    public final long latitude;
    public final long langitude;
    public String name;
    public int bikeNum;
    public String locationDesc;

    public Station(long id, long latitude, long langitude, String name, int bikeNum, String desc) {
        this.id = id;
        this.latitude = latitude;
        this.langitude = langitude;

        this.name = name;
        this.bikeNum = bikeNum;
        this.locationDesc = desc;
    }
}
