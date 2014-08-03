package com.example.lightbike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.lightbike.R;

public class ProfileUI extends BaseActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_main);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		finish();
		Intent intent = new Intent();
		intent.setClass(ProfileUI.this, DashboardActivity.class);
		startActivity(intent);
		return super.onTouchEvent(event);
	}

}
