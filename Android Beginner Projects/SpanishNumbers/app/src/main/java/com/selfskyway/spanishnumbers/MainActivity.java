package com.selfskyway.spanishnumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView one,two,three,four,five,six,seven,eight,nine,ten;
    protected SoundPool soundPool;
    protected int oneS,twoS,threeS,fourS,fiveS,sixS,sevenS,eightS,nineS,tenS;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getid();

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        soundPool = new SoundPool(5, audioManager.STREAM_MUSIC, 0);

        setSP();

        onClick(one,oneS);
        onClick(two,twoS);
        onClick(three,threeS);
        onClick(four,fourS);
        onClick(five,fiveS);
        onClick(six,sixS);
        onClick(seven,sevenS);
        onClick(eight,eightS);
        onClick(nine,nineS);
        onClick(ten,tenS);
    }

    void getid()
    {
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        ten = findViewById(R.id.ten);
    }

    void setSP()
    {
        oneS = soundPool.load(getApplicationContext(),R.raw.one,1);
        twoS = soundPool.load(this,R.raw.two, 1);
        threeS = soundPool.load(this,R.raw.three,1);
        fourS = soundPool.load(this,R.raw.four,1);
        fiveS = soundPool.load(getApplicationContext(),R.raw.five,1);
        sixS = soundPool.load(getApplicationContext(),R.raw.six,1);
        sevenS = soundPool.load(getApplicationContext(),R.raw.seven,1);
        eightS = soundPool.load(getApplicationContext(),R.raw.eight,1);
        nineS = soundPool.load(getApplicationContext(),R.raw.nine,1);
        tenS = soundPool.load(getApplicationContext(),R.raw.ten,1);
    }

    void play(int soundID)
    {
        soundPool.autoPause();
        soundPool.play(soundID, 1, 1, 1, 0, 1f);
    }

    void onClick(TextView tv, final int soundID)
    {
        tv.setSoundEffectsEnabled(false);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(soundID);
            }
        });
    }
}
