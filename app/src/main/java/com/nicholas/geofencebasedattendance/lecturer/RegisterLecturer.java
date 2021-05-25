package com.nicholas.geofencebasedattendance.lecturer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.R;

public class RegisterLecturer extends AppCompatActivity {
    private EditText editTextName,editTextNumber, editTextEmail,editTextPassword;
    private Button register;
    private TextView alregistered;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_lecturer);

        //hooks to the widgets
        editTextName=findViewById(R.id.lecName);
        editTextNumber=findViewById(R.id.staffNo);
        editTextEmail=findViewById(R.id.lecEmail);
        alregistered=findViewById(R.id.alreadyregistered);
        editTextPassword=findViewById(R.id.password);
        register=findViewById(R.id.registerLecturer);
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressbar);

        //mAuth Database Reference
        mAuth=FirebaseAuth.getInstance();

        alregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterLecturer.this,LecturerLogin.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here create a view string variables where the input is conversted into a string
                String name=editTextName.getText().toString().trim();
                String email=editTextEmail.getText().toString().trim();
                String number=editTextNumber.getText().toString().trim();
                String password=editTextPassword.getText().toString().trim();

                //do some validations for the edittext
                if(name.isEmpty()){
                    editTextName.setError("full_name required");
                    editTextName.requestFocus();
                    return;
                }
                if(number.isEmpty()){
                    editTextNumber.setError("phone number required");
                    editTextNumber.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextEmail.setError("email required");
                    editTextEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    editTextPassword.setError("fill password");
                    editTextPassword.requestFocus();
                    return;
                }
                if(password.length()<6){
                    editTextPassword.setError("min password characters should be six");
                    editTextPassword.requestFocus();
                    return;
                }
                //set the visibility of the progresssbar
                progressBar.setVisibility(View.VISIBLE);
                //here call our mauth object for firebase authentication
                mAuth.createUserWithEmailAndPassword(email,password)
                        //check if the user has been registered ,,,by adding on complete listener and
                        // then implement onComplete listener auth result
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //check if the task has been completed, if the user has been registered
                                if(task.isSuccessful()){
                                    //create user object
                                    LecturerData userData=new LecturerData(name,number,email,password);
                                    //send the user object to realtime database(firebase)

                                    //call firebase database object
                                    FirebaseDatabase.getInstance().getReference("Lecturer")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterLecturer.this,"registration successful",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                                Intent intent=new Intent(RegisterLecturer.this,LecturerLogin.class);
                                                startActivity(intent);
                                            }else{
                                                Toast.makeText(RegisterLecturer.this,"registration faled",Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }

                                        }
                                    });

                                }else{
                                    Toast.makeText(RegisterLecturer.this,"please Try again",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);

                                }
                            }
                        });
            }
        });
    }
}