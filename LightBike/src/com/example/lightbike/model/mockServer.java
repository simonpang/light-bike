package com.example.lightbike.model;

import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sim on 8/2/14.
 */
public class MockServer {

    public static List<Station> stations = new ArrayList<Station>();
    public static HashMap<Long, List<Bike>> bikesInStation = new HashMap<Long, List<Bike>>();
    static  {
        stations.add(new Station(1, 22.549596f, 113.957384f, "test", 5, "best station"));
        stations.add(new Station(2, 22.557906f, 113.958184f, "test", 5, "best station"));
        stations.add(new Station(3, 22.559696f, 113.952984f, "test", 5, "best station"));
        stations.add(new Station(4, 22.565696f, 113.957384f, "test", 5, "best station"));
        stations.add(new Station(5, 22.580296f, 113.956434f, "test", 5, "best station"));
        stations.add(new Station(6, 22.571606f, 113.955694f, "test", 5, "best station"));

        for (Station station : stations) {
            List<Bike> bikes = new ArrayList<Bike>();
            for (int i = 0; i< 5; ++i) {
                bikes.add(new Bike(1000*station.id + i, station.latitude, station.longitude, "NICE BIKE", "WUSI", Bike.STATUS.LOCK) );
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
