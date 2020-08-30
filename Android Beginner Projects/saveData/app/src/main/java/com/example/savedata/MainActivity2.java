package com.example.savedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    EditText name,adress,num;
    Button buttn;
    DatabaseReference reff;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        adress=findViewById(R.id.adress);
        num=findViewById(R.id.num);
        buttn=findViewById(R.id.button);
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        Toast.makeText(MainActivity2.this,"firebase connection is success",Toast.LENGTH_LONG).show();
        member=new Member();
        buttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long phn=Long.parseLong(num.getText().toString().trim());
                member.setAdress(adress.getText().toString().trim());
                member.setName(name.getText().toString().trim());
                member.setNum(phn);
                reff.push().setValue(member);
                Toast.makeText(MainActivity2.this,"data insert succesfull",Toast.LENGTH_LONG).show();
            }
        });
    }


}

