package com.example.lightbike.ui;

import java.io.File;

import com.example.lightbike.R;
import com.example.lightbike.qrcode.MipcaActivityCapture;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity  extends BaseActivity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnBluetooth = (Button) findViewById(R.id.btn_bluetooth);
		
		btnBluetooth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, BluetoothUI.class);
				startActivity(intent);
			}
		});
		
		Button btnMap = (Button) findViewById(R.id.btn_map);
		
		btnMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, LocationUI.class);
				startActivity(intent);
			}
		});
		
		Button btnQrscan = (Button) findViewById(R.id.btn_qrscan);
		
		btnQrscan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, MipcaActivityCapture.class);
				startActivity(intent);
			}
		});
		
	}
	

	protected void onResume(){
		super.onResume();
																//onResume Process by BlunoLibrary
	}
	
	

}