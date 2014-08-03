package com.example.lightbike.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.lightbike.R;

/**
 * Created by sim on 8/3/14.
 */
public class StationUI extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        Button button = (Button) findViewById(R.id.save_bike_btn);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setResult(Activity.RESULT_OK, null);
		        finish();
			}
		});
    }
    
    
    
    
}
