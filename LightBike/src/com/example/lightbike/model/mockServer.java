package com.example.lightbike.model;

import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sim on 8/2/14.
 */
public class mockServer {

    public static List<Station> stations = new ArrayList<Station>();
    public static HashMap<Long, List<Bike>> bikesInStation = new HashMap<Long, List<Bike>>();
    static  {
        stations.add(new Station(1, 100, 100, "test", 5, "best station"));
        stations.add(new Station(2, 101, 101, "test", 5, "best station"));
        stations.add(new Station(3, 101, 101, "test", 5, "best station"));
        stations.add(new Station(4, 101, 101, "test", 5, "best station"));
        stations.add(new Station(5, 101, 101, "test", 5, "best station"));
        stations.add(new Station(6, 101, 101, "test", 5, "best station"));

        for (Station station : stations) {
            List<Bike> bikes = new ArrayList<Bike>();
            for (int i = 0; i< 5; ++i) {
                bikes.add(new Bike(1000*station.id + i, station.latitude, station.langitude, "NICE BIKE", "WUSI", Bike.STATUS.LOCK) );
            }
            bikesInStation.put(station.id , bikes);
        }
    }

    public static List<Bike> mockBike(long stationId) {
        return bikesInStation.get(stationId);
    }

    public static List<Station> mockStations(LatLng currentPosition) {
        return stations;
    }
}
