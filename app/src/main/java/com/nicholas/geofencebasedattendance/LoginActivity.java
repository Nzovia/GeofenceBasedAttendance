package com.nicholas.geofencebasedattendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nicholas.geofencebasedattendance.Student.RegisterStudent;
import com.nicholas.geofencebasedattendance.Student.StudentDashboard;

public class LoginActivity extends AppCompatActivity {
   private EditText emailEditText,passwordEditText;
   private Button login,register;
   private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acivity);

        //create hooks
        emailEditText=findViewById(R.id.email);
        passwordEditText=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.registerU);
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            // there was an error
                            if (password.length() < 6) {
                                Toast.makeText(getApplicationContext(),"six or more characters",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed, try again", Toast.LENGTH_LONG).show();
                            }

                        }
                        else {
                            Intent intent = new Intent(LoginActivity.this, StudentDashboard.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
//                Intent login=new Intent(LoginActivity.this, StudentDashboard.class);
//                startActivity(login);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterStudent.class);
                startActivity(intent);
            }
        });

    }
}