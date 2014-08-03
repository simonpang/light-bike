package com.example.lightbike.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.lightbike.R;
import com.example.lightbike.app.BikeApplication;
import com.example.lightbike.ble.BlunoLibrary;

import java.util.logging.Handler;

/**
 * Created by sim on 8/3/14.
 */
public class StolenUI extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stolen);

        ImageView freeBtn = (ImageView) findViewById(R.id.button);
        freeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFree();
            }
        });

        ImageView callMe = (ImageView) findViewById(R.id.button2);
        callMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传入服务， parse（）解析号码
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13186086906"));
                //通知activtity处理传入的call服务
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("BlUNOActivity onResume");
        ((BikeApplication)getApplication()).tryConnect();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setFree() {
        ((BikeApplication)getApplication()).sendCommand("F");
    }
}
