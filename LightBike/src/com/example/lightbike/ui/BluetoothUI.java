package com.example.lightbike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.lightbike.R;
import com.example.lightbike.ble.BlunoLibrary;


public class BluetoothUI  extends BlunoLibrary {
	private TextView statusText;
	private Button lockBtn;
    private ImageButton unlockBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.qrcode_main);
        onCreateProcess();														//onCreate Process by BlunoLibrary
        
        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200
		
        lockBtn = (Button) findViewById(R.id.lock_button);
        lockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialSend("L");
            }
        });

        unlockBtn = (ImageButton) findViewById(R.id.unlock_button);
        unlockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialSend("U");
            }
        });
        statusText = (TextView) findViewById(R.id.tv_status);

    }

	protected void onResume(){
		super.onResume();
		System.out.println("BlUNOActivity onResume");
		onResumeProcess();
        statusText.post(new Runnable() {
            @Override
            public void run() {
                buttonScanOnClickProcess();
            }
        });
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
		super.onActivityResult(requestCode, resultCode, data);
	}
	
    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();														//onPause Process by BlunoLibrary
    }
	
	protected void onStop() {
		super.onStop();
		onStopProcess();														//onStop Process by BlunoLibrary
	}
    
	@Override
    protected void onDestroy() {
        super.onDestroy();	
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

	@Override
	public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
		switch (theConnectionState) {											//Four connection state
		case isConnected:
			statusText.setText("Connected");
			break;
		case isConnecting:
			statusText.setText("Connecting");
			break;
		case isToScan:
			statusText.setText("Scan");
			break;
		case isScanning:
			statusText.setText("Scanning");
			break;
		case isDisconnecting:
			statusText.setText("isDisconnecting");
			break;
		default:
			break;
		}
	}

	@Override
	public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
		//serialReceivedText.append(theString);							//append the text into the EditText
		//The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.
        if (theString.equals("S")) {
            Intent intent = new Intent();
            intent.setClass(BluetoothUI.this, StolenUI.class);
            startActivity(intent);
        }
	}
}