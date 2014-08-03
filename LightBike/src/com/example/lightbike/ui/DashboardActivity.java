package com.example.lightbike.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.lightbike.R;
import com.example.lightbike.qrcode.MipcaActivityCapture;

public class DashboardActivity extends BaseActivity {

    private boolean qrFlag = false;
    private Button scanQRButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		Button viewMapButton = (Button) findViewById(R.id.viewMapButton);
		scanQRButton = (Button) findViewById(R.id.scanQRButton);
		
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
                if (!qrFlag) {
                    Intent intent = new Intent(DashboardActivity.this, MipcaActivityCapture.class);
                    startActivity(intent);
                    qrFlag = true;
                } else {
                    Intent intent = new Intent();
                    intent.setClass(DashboardActivity.this, BluetoothUI.class);
                    startActivity(intent);
                }
			}
		});
		
		Button btnBluetooth = (Button) findViewById(R.id.btn_bluetooth);
		btnBluetooth.setVisibility(View.INVISIBLE);
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
    protected void onResume() {
        super.onResume();
        if (qrFlag) {
            scanQRButton.setText("单车控制");
        }
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
