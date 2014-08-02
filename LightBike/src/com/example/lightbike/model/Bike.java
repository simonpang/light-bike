package com.example.lightbike.model;

/**
 * Created by sim on 8/2/14.
 */

import com.baidu.mapapi.model.LatLng;

/**
 * //定义Maker坐标点
 LatLng point = new LatLng(39.963175, 116.400244);
 //构建Marker图标
 BitmapDescriptor bitmap = BitmapDescriptorFactory
 .fromResource(R.drawable.icon_marka);
 //构建MarkerOption，用于在地图上添加Marker
 OverlayOptions option = new MarkerOptions()
 .position(point)
 .icon(bitmap);
 //在地图上添加Marker，并显示
 mBaiduMap.addOverlay(option);
 */
public class Bike {
    public long id;
    public long latitude;
    public long langitude;
    public String name;
    public String password;
    public int status;
    public interface STATUS {
        public static final int UNLOCK = 0;
        public static final int LOCK = 1;
        public static final int STOLEN = 2;
    }

    public Bike(long id, long latitude, long langitude, String name , String password, int status) {
        this.id = id;
        this.latitude = latitude;
        this.langitude = langitude;
        this.name = name;
        this.password = password;
        this.status = STATUS.LOCK;
    }
}
