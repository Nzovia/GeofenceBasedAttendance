package com.nicholas.geofencebasedattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nicholas.geofencebasedattendance.Student.RegisterStudent;
import com.nicholas.geofencebasedattendance.Student.StudentDashboard;

public class LoginAcivity extends AppCompatActivity {
   private EditText email,password;
   private Button login,register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acivity);

        //create hooks
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.registerU);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login=new Intent(LoginAcivity.this, StudentDashboard.class);
                startActivity(login);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginAcivity.this, RegisterStudent.class);
                startActivity(intent);
            }
        });

    }
}