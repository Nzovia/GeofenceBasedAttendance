package com.nicholas.geofencebasedattendance.lecturer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nicholas.geofencebasedattendance.R;

public class LecturerDashboard extends AppCompatActivity implements View.OnClickListener {
    CardView card1, card2, card3, card4, card5, card6;
    int cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_dashboard);


        //hooks
        card1 = findViewById(R.id.lecCard1);
        card2 = findViewById(R.id.lecCard2);
        card3 = findViewById(R.id.lecCard3);
        card4 = findViewById(R.id.lecCard4);
        card5 = findViewById(R.id.lecCard5);
        card6 = findViewById(R.id.lecCard6);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //perform action On OnClick
        switch (v.getId()) {
            case R.id.lecCard1:
                Intent intent = new Intent(LecturerDashboard.this, RegisteredStudents.class);
                startActivity(intent);
                break;
            case R.id.lecCard2:
                Intent i = new Intent(LecturerDashboard.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.lecCard3:
                Intent j = new Intent(LecturerDashboard.this, Attendance.class);
                startActivity(j);
                break;
            case R.id.lecCard4:
                Intent k = new Intent(LecturerDashboard.this, ChatsActivity.class);
                startActivity(k);
                break;
//            case R.id.lecCard5:
//                Intent l = new Intent(LecturerDashboard.this, RegisteredStudents.class);
//                startActivity(l);
//                break;
//            case R.id.lecCard6:
//                Intent m= new Intent(LecturerDashboard.this, RegisteredStudents.class);
//                startActivity(m);
//                break;

        }
    }
}