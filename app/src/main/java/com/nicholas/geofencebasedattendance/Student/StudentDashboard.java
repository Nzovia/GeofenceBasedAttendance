package com.nicholas.geofencebasedattendance.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nicholas.geofencebasedattendance.R;

public class StudentDashboard extends AppCompatActivity {
    private CardView card1,card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        card1=findViewById(R.id.attendclass);
        card2=findViewById(R.id.studentActivities);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentDashboard.this,AttendClass.class);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentDashboard.this, ClassPosts.class);
                startActivity(intent);
            }
        });
    }
}