package com.example.retrivedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
String s,r,t;
    TextView a,b,c;
    Button d;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);

d.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        reff=FirebaseDatabase.getInstance().getReference().child("Member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    s=dataSnapshot1.child("name").getValue().toString();
                    r=dataSnapshot1.child("adress").getValue().toString();
                    t=dataSnapshot1.child("num").getValue().toString();
                }
                a.setText(s);
                b.setText(r);
                c.setText(t);
    //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();


                //                String adress=dataSnapshot.child("adress").getValue().toString();
//                String name=dataSnapshot.child("name").getValue().toString();
//                String num=dataSnapshot.child("num").getValue().toString();
////                a.setText(adress);
//                b.setText(name);
//                c.setText(num);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
});



    }

}

