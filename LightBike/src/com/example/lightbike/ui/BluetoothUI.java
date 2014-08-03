package com.example.lightbike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.lightbike.R;
import com.example.lightbike.app.BikeApplication;
import com.example.lightbike.ble.BlunoLibrary;


public class BluetoothUI extends BaseActivity {
	private TextView statusText;
	private Button lockBtn;
    private ImageButton unlockBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.qrcode_main);


		
        lockBtn = (Button) findViewById(R.id.lock_button);
        lockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BikeApplication)getApplication()).sendCommand("L");
            }
        });

        unlockBtn = (ImageButton) findViewById(R.id.unlock_button);
        unlockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BikeApplication)getApplication()).sendCommand("U");
            }
        });
        statusText = (TextView) findViewById(R.id.tv_status);

    }

	protected void onResume(){
		super.onResume();
		System.out.println("BlUNOActivity onResume");
        statusText.post(new Runnable() {
            @Override
            public void run() {
                ((BikeApplication)getApplication()).tryConnect();
            }
        });
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
	
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
	
	protected void onStop() {
		super.onStop();
	}
    
	@Override
    protected void onDestroy() {
        super.onDestroy();	
    }

}