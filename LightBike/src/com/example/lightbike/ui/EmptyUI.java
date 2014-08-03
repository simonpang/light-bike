package com.example.lightbike.ui;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sim on 8/3/14.
 */
public class EmptyUI extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setClass(EmptyUI.this, StolenUI.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
