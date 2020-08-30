package com.selfskyway.dialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView zero, one, two, three, four, five, six, seven, eight, nine, hash, star, call, dialView, top1, top2, top3, erase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getId();
        if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)&&((ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE))!= PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
        }
        if((ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE))== PackageManager.PERMISSION_DENIED)
        {
            this.finish();
        }

        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialView.setText(top1.getText());
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialView.setText(top2.getText());
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialView.setText(top3.getText());
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('0');
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('1');
            }
        });


        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('2');
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('3');
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('4');
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('5');
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('6');
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('7');
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('8');
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('9');
            }
        });
        hash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('#');
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNo('*');
            }
        });
        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erase();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall();
            }
        });


    }

    void addNo(char no)
    {
        dialView.setText(dialView.getText().toString()+no);
    }

    void erase()
    {
        String prevNo = dialView.getText().toString();
        if(prevNo.length() > 0)
            dialView.setText(prevNo.substring(0,(dialView.length()-1)));
    }

    void makeCall()
    {

        String dialViewText = dialView.getText().toString();
        if(dialViewText.length() <= 0)
        {
            return;
        }
        if(dialViewText.contains("#")){
            dialViewText = dialViewText.replace("#","%23");
        }
        if(dialViewText.charAt(0)!= '*' && dialViewText.charAt(0)!= '#')
        {
            dialViewText= "+91"+dialViewText;
        }
        String uri = "tel:"+dialViewText;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);

        top1.setText(top2.getText());
        top2.setText(top3.getText());
        top3.setText(dialView.getText());
        dialView.setText("");
    }

    void getId()
    {
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        hash = findViewById(R.id.hash);
        star = findViewById(R.id.star);
        call = findViewById(R.id.call);
        dialView = findViewById(R.id.dialView);
        top1 = findViewById(R.id.top1);
        top2 = findViewById(R.id.top2);
        top3 = findViewById(R.id.top3);
        erase = findViewById(R.id.erase);
    }
}


// #made by selfskyway