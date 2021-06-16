package com.nicholas.geofencebasedattendance.Student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nicholas.geofencebasedattendance.R;

public class AttendClass extends AppCompatActivity {
    private TextView textViewlongitude, textViewlatitude;
    private Button attendClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_class);

        textViewlongitude=findViewById(R.id.displaylongititude);
        textViewlatitude=findViewById(R.id.displaylatitude);
        attendClass=findViewById(R.id.buttonAttend);


        //onclick method for the button
        attendClass.setOnClickListener(v -> showDialog());
    }

    private void showDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view= LayoutInflater.from(this).inflate(R.layout.authenticate_attendance,null);
        builder.setView(view);
        builder.create().show();
    }
}