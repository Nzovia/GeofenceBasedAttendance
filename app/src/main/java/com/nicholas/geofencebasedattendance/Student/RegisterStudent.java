package com.nicholas.geofencebasedattendance.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.LoginActivity;
import com.nicholas.geofencebasedattendance.R;

public class  RegisterStudent extends AppCompatActivity {
    private EditText nameEditText, registrationNoEditText, emailEditText, passwordEditText, confirmpasswordEditText;
    private Button register;
    private FirebaseAuth mAuth;
    ProgressBar progressbar;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        //widget hooks
        nameEditText = findViewById(R.id.name);
        registrationNoEditText = findViewById(R.id.studentNo);
        emailEditText = findViewById(R.id.studentEmail);
        passwordEditText = findViewById(R.id.studentPassword);
        confirmpasswordEditText = findViewById(R.id.confirmPassword);

        //others
        progressbar = findViewById(R.id.progressbar);

        //things to do with firebase
        //firebase connection
        //get the instance  of the database
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

        //get reference for our database
        //databaseReference = firebaseDatabase.getReference("MyDb");


        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });
    }
    private void registerStudent(){

        //get the data from the edittext fields..and convert it into strings

        String name = nameEditText.getText().toString().trim();
        String registrationNo = registrationNoEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmpassword = confirmpasswordEditText.getText().toString().trim();

        //check if all the textfields are field with data
        //do some validations for the edittext
        if (name.isEmpty()) {
            nameEditText.setError("full_name required");
            nameEditText.requestFocus();
            return;
        }
        if (registrationNo.isEmpty()) {
            registrationNoEditText.setError("registration number required");
            registrationNoEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("email required");
            emailEditText.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordEditText.setError("min password characters should be six");
            passwordEditText.requestFocus();
            return;
        }
        if (confirmpassword.equals(password)) {
            confirmpassword = password;
            // Toast.makeText(getApplicationContext(),"passwords match",Toast.LENGTH_SHORT).show();
        } else {
            confirmpasswordEditText.setError("no match");
            confirmpasswordEditText.requestFocus();
            return;
        }
        //set the visibility of the progresssbar
        progressbar.setVisibility(View.VISIBLE);

        //here call our mauth object for firebase authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                //check if the user has been registered ,,,by adding on complete listener and
                // then implement onComplete listener auth result
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //check if the task has been completed, if the user has been registered
                        if (task.isSuccessful()) {
                            //create user object
                            studentItem studentItem = new studentItem(name, registrationNo, email);

                            //call firebase database object
                            firebaseDatabase.getReference("myDB")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(studentItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterStudent.this, "registration successful", Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.GONE);
                                        Intent intent = new Intent(RegisterStudent.this, LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(RegisterStudent.this, "registration failed", Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.GONE);
                                    }

                                }

                            });

                        } else {
                            Toast.makeText(RegisterStudent.this, "please Try again", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);

                        }


                    }


                });


    }
}