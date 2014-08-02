package com.example.lightbike.ui;

import com.example.lightbike.R;
import com.example.lightbike.R.id;
import com.example.lightbike.R.layout;
import com.example.lightbike.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DashboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		Button viewMapButton = (Button) findViewById(R.id.viewMapButton);
		Button scanQRButton = (Button) findViewById(R.id.scanQRButton);
		
		viewMapButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(DashboardActivity.this, LocationUI.class);
				startActivity(intent);
			}
		});
		
		scanQRButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(DashboardActivity.this, QRCodeUI.class);
				startActivity(intent);
			}
		});
		
		Button btnBluetooth = (Button) findViewById(R.id.btn_bluetooth);
		
		btnBluetooth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(DashboardActivity.this, BluetoothUI.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
