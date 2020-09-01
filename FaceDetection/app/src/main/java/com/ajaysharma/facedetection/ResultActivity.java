package com.ajaysharma.facedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setContentView (R.layout.activity_result);

        textView = findViewById (R.id.textview);

        Bundle bundle = getIntent ().getExtras ();

        String text = bundle.getString ("res");

        textView.setText (text);
    }
}
