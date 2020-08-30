package com.selfskyway.truthdare;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView spin ;
    ImageView bottel ;

    SoundPool soundPool;
    AudioManager audioManager;
    int backNoise, backNoiseId;

    float BASE_ROTATION_DEGREES = (float)(3000);
    int DURATION = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        soundPool = new SoundPool(1, audioManager.STREAM_MUSIC, 0);
        backNoise = soundPool.load(getApplicationContext(),R.raw.effect,1);

        bottel = findViewById(R.id.bottel);
        spin = findViewById(R.id.spin);

        spinClickListener();
    }


    private void hide(View v, int duration) {
        v.animate().alpha(0f).setDuration(duration);
    }

    void stopBackNoice()
    {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                soundPool.setVolume(backNoiseId, .3f,.3f);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        soundPool.stop(backNoiseId);
                    }
                }, 1000);
            }
        }, DURATION-1000);
    }

    void spinClickListener()
    {
        bottel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spin.setText("Bottle Spinning");
                hide(spin, 2000);

                soundPool.autoPause();
                backNoiseId = soundPool.play(backNoise, 1, 1, 1, 1, 1f);

                float deg = bottel.getRotation()+BASE_ROTATION_DEGREES+((float)Math.random()*360F);  // ratation to rotate
                bottel.animate().rotation(deg).setDuration(DURATION).setInterpolator(new AccelerateDecelerateInterpolator());

                 stopBackNoice();


            }
        });
    }

}
