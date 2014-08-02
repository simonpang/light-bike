package com.example.lightbike.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.lightbike.R;

public class QRCodeUI extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcode_main);
		
		
		ImageButton mButton = (ImageButton) findViewById(R.id.button);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent();
//				intent.setClass(QRCodeUI.this, MipcaActivityCapture.class);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
	}
	
	
//	@Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//		case SCANNIN_GREQUEST_CODE:
//			if(resultCode == RESULT_OK){
////				Bundle bundle = data.getExtras();
////				mTextView.setText(bundle.getString("result"));
////				//��ʾ
////				mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
//			}
//			break;
//		}
//    }	

}
