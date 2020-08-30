package com.selfskyway.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Character.isDigit;

public class MainActivity extends AppCompatActivity {
    Button us, ca, euro, aus;
    EditText input;
    TextView output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getid();
        onclick(us,0.015f, "US $");
        onclick(ca,0.019f, "CA $");
        onclick(euro,0.013f, "EU €");
        onclick(aus,0.021f, "AUS $");
    }

    void getid()
    {
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);

        us = findViewById(R.id.us);
        ca = findViewById(R.id.ca);
        euro = findViewById(R.id.euro);
        aus = findViewById(R.id.aus);
    }

    void onclick(Button b, final float outputCur, final String outputCurS)
    {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert(outputCurS, outputCur);
            }
        });
    }

    void convert(String sign, float outputCur)
    {
        String inputS =input.getText().toString();
        int inputSLen = inputS.length();

        if(inputSLen <= 0 )
        {
            return;
        }
        if(inputSLen > 10)
        {
            inputS = inputS.substring(0,10);
        }
        float inputI = Float.parseFloat(inputS);

        if(inputI < 1f )
        {
            return;
        }

        float outputf = inputI * outputCur;
        String outputS = (String.format("%s%.3f", sign, outputf));
        char outputSA[] = outputS.toCharArray();
        int outputSALen = outputS.length();

        String outputSS = "";
        for(int i = outputSALen-1; i > 0; i--)
        {
            if(outputSA[i] == '0' || outputSA[i] == '.')
            {
                continue;
            }
            else if(!isDigit((outputSA[i])))
            {
                continue;
            }
            else{
                outputSS = outputS.substring(0,i+1);
                break;
            }
        }
        outputSALen = outputSS.length();
        if(outputSALen > 1)
        {
            output.setTextSize(30-(outputSALen-1));
        }
        input.setText(inputS);
        output.setText(outputSS);
    }
}

// # Made By selfskyway















//# AJay