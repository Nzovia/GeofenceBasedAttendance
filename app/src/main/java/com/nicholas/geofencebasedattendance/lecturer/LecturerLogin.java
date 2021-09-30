package com.nicholas.geofencebasedattendance.lecturer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nicholas.geofencebasedattendance.R;

public class LecturerLogin extends AppCompatActivity {
    private EditText lecturerEmail, lecturerPassword;
    private TextView forgotPassword, register;
    private Button lecturerLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_login);

        //creating hooks
        lecturerEmail=findViewById(R.id.lecEmail);
        lecturerPassword=findViewById(R.id.lecPassword);
        forgotPassword=findViewById(R.id.forgotpassword);
        register=findViewById(R.id.registerLecturer);
        lecturerLogin=findViewById(R.id.leclogin);
        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LecturerLogin.this,RegisterLecturer.class);
                startActivity(intent);
            }
        });
        lecturerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = lecturerPassword.getText().toString().trim();
                String email = lecturerEmail.getText().toString().trim();

                if (email.isEmpty()) {
                    lecturerEmail.setError("email required");
                    lecturerEmail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LecturerLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            // there was an error
                            if (password.length() < 6) {
                                Toast.makeText(getApplicationContext(),"six or more characters",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LecturerLogin.this, "Login failed, try again", Toast.LENGTH_LONG).show();
                            }

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LecturerLogin.this, LecturerDashboard.class);
                            startActivity(intent);
                           // finish();
                        }
                    }
                });
            }
        });

    }
}