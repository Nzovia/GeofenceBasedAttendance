package com.nicholas.geofencebasedattendance.Student;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.R;
import com.nicholas.geofencebasedattendance.lecturer.FencesAdapter;
import com.nicholas.geofencebasedattendance.lecturer.FencesData;


public class AttendClass extends AppCompatActivity {
    RecyclerView attendanceLinks;
    DatabaseReference myDB;
    FencesAdapter fenceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_class);

       //create an instance of the database being referenced from
        attendanceLinks = findViewById(R.id.attendanceDataRecycler);
        //set the recyclerview linearly
        attendanceLinks.setLayoutManager(new LinearLayoutManager(this));

        myDB = FirebaseDatabase.getInstance().getReference().child("ClassFences");

        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<FencesData> options
                = new FirebaseRecyclerOptions.Builder<FencesData>()
                .setQuery(myDB, FencesData.class)  // .setQuery(myDB, studentItem.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        fenceAdapter = new FencesAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        attendanceLinks.setAdapter(fenceAdapter);
    }


    @Override
    protected void onStart() {
        fenceAdapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fenceAdapter.stopListening();
    }
}