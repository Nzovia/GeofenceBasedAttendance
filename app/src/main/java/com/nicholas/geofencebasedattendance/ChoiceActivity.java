package com.nicholas.geofencebasedattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nicholas.geofencebasedattendance.Student.StudentDashboard;
import com.nicholas.geofencebasedattendance.lecturer.LecturerDashboard;

public class ChoiceActivity extends AppCompatActivity {
    Button student, lecturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        //create hooks
        student=findViewById(R.id.student);
        lecturer=findViewById(R.id.lecturer);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChoiceActivity.this, LoginAcivity.class);
                startActivity(intent);
                //finish();
            }
        });
        lecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChoiceActivity.this, LecturerDashboard.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}