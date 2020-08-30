package com.selfskyway.discolight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar control;
    Switch swich;
    CameraManager cameraManager;
    String cameraId;
    TextView tv;
    blink sr = new blink();
    Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getId();
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        getPermissions();
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        }catch (Exception e)
        {
            exit();
        }

        swichCheck();
        seekB();

    }

    void swichCheck()
    {
        swich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flashOnOff();
            }
        });
    }
    void getId()
    {
        control = findViewById(R.id.seek);
        swich = findViewById(R.id.swich);
        tv = findViewById(R.id.text);
    }

    void getPermissions()
    {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
        {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},1);
            }
        }
    }

    void seekB()
    {
        control.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sr.frequency = i;

                if(sr.frequency <= 0)
                {
                    swich.setChecked(false);
                }
                else{
                    flashOnOff();
                    swich.setChecked(true);
                }
                tv.setText(Integer.toString(sr.frequency));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    void flashOnOff()
    {
        if (t != null) {
            t.interrupt();
            flashOff();
        }

        if (swich.isChecked()) {
            t = new Thread(sr);
            t.start();
            return ;

            
        }

    }

    void flashOn()
    {
        try{
            cameraManager.setTorchMode(cameraId,true);
        }catch (Exception e)
        {
            exit();
        }
    }


    void flashOff()
    {
        try{
            cameraManager.setTorchMode(cameraId,false);

        }catch (Exception e)
        {
            exit();
        }
    }

    void exit()
    {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    private class blink implements Runnable {

        int frequency;
        @Override
        public void run() {
            try {
                while (true) {
                    flashOn();
                    Thread.sleep(150 - frequency);
                    flashOff();
                    Thread.sleep(150-frequency);
                }
            }
            catch(Throwable ignored) {}
        }
    }
}

//# made  by selfskyway










//Aj