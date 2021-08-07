package com.nicholas.geofencebasedattendance.lecturer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.R;

public class   ShareFence extends AppCompatActivity {
    private TextView passedLat, passedLong;
    private EditText unitName, unitCode;
    private Button sharedata;
    private FirebaseAuth mAuth;

    DatabaseReference mydatabases;
   // FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_fence);

        passedLat=findViewById(R.id.latText);
        passedLong=findViewById(R.id.longitudeText);
        unitName=findViewById(R.id.unitname);
        unitCode=findViewById(R.id.unitcode);
        sharedata=findViewById(R.id.sharefence);

        //getting Intent
        Intent intent=getIntent();
        String latitude=intent.getStringExtra("latitude");
        passedLat.setText(latitude);
        String longitude=intent.getStringExtra("longitude");
        passedLong.setText(longitude);

        //firebase connection
        //getting instance of the firebase
        mAuth = FirebaseAuth.getInstance();
        mydatabases = FirebaseDatabase.getInstance().getReference().child("ClassFences");
        sharedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFenceData();
            }
        });

    }

    private void shareFenceData() {
        //this method helps you get data from both textView

        String id=mydatabases.push().getKey();
        String latitude = passedLat.getText().toString().trim();
        String longitude = passedLong.getText().toString().trim();
        String unitname = unitName.getText().toString().trim();
        String unitcode = unitCode.getText().toString().trim();

        //valiadate if the textfields are empty or not
        if (unitname.isEmpty()){
            unitName.setError("unit name required !!");
            unitName.requestFocus();
            return;
        }
        if(unitcode.isEmpty()){
            unitCode.setError("unit code required");
            unitCode.requestFocus();
            return;
        }

        //create an object that fetchs data from the fence model/ fencesData
        FencesData fencesData = new FencesData(id,latitude,longitude,unitname,unitcode);
        //now call the firebase object
        mydatabases.child(id).setValue(fencesData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "fence shared successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });

    }
}