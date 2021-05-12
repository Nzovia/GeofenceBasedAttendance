package com.nicholas.geofencebasedattendance.lecturer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nicholas.geofencebasedattendance.R;

public class LecturerDashboard extends AppCompatActivity {
    CardView card1,card2,card3,card4,card5,card6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_dashboard);


        //hooks
        card1=findViewById(R.id.lecCard1);
        card2=findViewById(R.id.lecCard2);
        card3=findViewById(R.id.lecCard3);



        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LecturerDashboard.this, RegisteredStudents.class);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LecturerDashboard.this, GeoFence.class);
                startActivity(intent);
            }
        });
    }
}