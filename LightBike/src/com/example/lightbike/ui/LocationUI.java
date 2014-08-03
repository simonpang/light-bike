package com.example.lightbike.ui;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfigeration;
import com.baidu.mapapi.map.MyLocationConfigeration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.lightbike.R;
import com.example.lightbike.model.MockServer;
import com.example.lightbike.model.Station;

import java.util.HashMap;
import java.util.List;

/**
 * 此demo用来展示如何结合定位SDK实现定位，并使用MyLocationOverlay绘制定位位置 同时展示如何使用自定义图标绘制并点击时弹出泡泡
 * 
 */
public class LocationUI extends BaseActivity {

    private static final String TAG = "LocationUI";
    
	// 定位相关
    private InfoWindow mInfoWindow;
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	private LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;
    private static final float DEFAULT_ZOOM_LEVEL = 17.0f;
	MapView mMapView;
	BaiduMap mBaiduMap;
	// UI相关
	OnCheckedChangeListener radioButtonListener;
	Button requestLocButton;
	boolean isFirstLoc = true;// 是否首次定位

    private HashMap<Marker, Station> markerStationHashMap = new HashMap<Marker, Station>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		requestLocButton = (Button) findViewById(R.id.button1);
		mCurrentMode = LocationMode.NORMAL;
		OnClickListener btnClickListener = new OnClickListener() {
			public void onClick(View v) {
				switch (mCurrentMode) {
				case NORMAL:
					requestLocButton.setText("跟随");
					mCurrentMode = LocationMode.FOLLOWING;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfigeration(
									mCurrentMode, true, mCurrentMarker));
					break;
				case COMPASS:
					requestLocButton.setText("普通");
					mCurrentMode = LocationMode.NORMAL;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfigeration(
									mCurrentMode, true, mCurrentMarker));
					break;
				case FOLLOWING:
					requestLocButton.setText("罗盘");
					mCurrentMode = LocationMode.COMPASS;
					mBaiduMap
							.setMyLocationConfigeration(new MyLocationConfigeration(
									mCurrentMode, true, mCurrentMarker));
					break;
				}
			}
		};
		requestLocButton.setOnClickListener(btnClickListener);
		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfigeration(
                mCurrentMode, true, null));
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(DEFAULT_ZOOM_LEVEL);
        mBaiduMap.setMapStatus(msu);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
            LatLng ll = null;
			if (isFirstLoc) {
				isFirstLoc = false;
				ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);

                Log.e(TAG, "Location: " + location.getLatitude() + ": " + location.getLongitude());
			}

            drawStation(ll);
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
        mMapView.getMap().setOnMarkerClickListener(null);
        super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
        BaiduMap.OnMarkerClickListener markerClickListener = new BaiduMap.OnMarkerClickListener() {
            /**
             * 地图 Marker 覆盖物点击事件监听函数
             * @param marker 被点击的 marker
             */
            public boolean onMarkerClick(Marker marker){
                Log.i(TAG, "onMarkerClick");
//                Button button = new Button(getApplicationContext());
//                button.setBackgroundResource(R.drawable.popup);
//                final LatLng ll = marker.getPosition();
//                Point p = mBaiduMap.getProjection().toScreenLocation(ll);
//                p.y -= 47;
//                LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
//                mInfoWindow = new InfoWindow(button, llInfo, new InfoWindow.OnInfoWindowClickListener() {
//                    @Override
//                    public void onInfoWindowClick() {
//                        mBaiduMap.hideInfoWindow();
//                    }
//                });
//                mBaiduMap.showInfoWindow(mInfoWindow);
                Intent intent = new Intent();
                intent.setClass(LocationUI.this, StationUI.class);
                startActivity(intent);
                return true;
            }
        };
        mMapView.getMap().setOnMarkerClickListener(markerClickListener);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

    public void drawStation(LatLng ll) {
        List<Station> stationList = MockServer.mockStations(ll);
        //定义Maker坐标点
        for (Station station : stationList) {
            LatLng point = new LatLng(station.latitude, station.longitude);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.icon_gcoding);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            Marker marker = (Marker) mBaiduMap.addOverlay(option);
            markerStationHashMap.put(marker, station);

        }
    }
}
