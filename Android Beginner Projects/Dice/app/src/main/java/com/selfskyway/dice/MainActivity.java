package com.selfskyway.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button roll;
    ImageView dice;
     int first;
    Thread thread = null;
    Boolean stop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll = findViewById(R.id.roll);
        dice = findViewById(R.id.dice);

        first = R.drawable.dice1;

//        Toast.makeText(this,(res3-res)+"",Toast.LENGTH_LONG).show();

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                roll.setEnabled(false);

                stop = false;
                final Handler myHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        updateUI((int)msg.obj);
                    }

                };

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    public void run() {
                        stop = true;
                        thread.interrupt();
                        roll.setEnabled(true);
                    }
                }, 2000);

                thread = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        while (!stop)
                        {
                            Message msg = myHandler.obtainMessage();

                            msg.obj = first+(int)(Math.random()*6);
                            myHandler.sendMessage(msg);

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                thread.start();


            }
        });

        }

    private void updateUI(int obj)
    {
        dice.setImageResource(obj);
    }
}



