package com.nicholas.geofencebasedattendance.lecturer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.R;
import com.nicholas.geofencebasedattendance.Student.studentItem;

import java.util.ArrayList;

public class RegisteredStudents extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button button;

    DatabaseReference myDB;
    studentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fence);

        button=findViewById(R.id.viewstudents);
        // Create a instance of the database and get
        // its reference

        recyclerView=findViewById(R.id.studentslist);
        //set the recyclerview linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // calling a method to get data from
        // Firebase and set data to list view
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                myDB=FirebaseDatabase.getInstance().getReference("myDB");
                // It is a class provide by the FirebaseUI to make a
                // query in the database to fetch appropriate data
                FirebaseRecyclerOptions<studentItem> options
                        = new FirebaseRecyclerOptions.Builder<studentItem>()
                        .setQuery(myDB, studentItem.class)
                        .build();
                // Connecting object of required Adapter class to
                // the Adapter class itself
                adapter = new studentAdapter(options);
                // Connecting Adapter class with the Recycler view*/
                recyclerView.setAdapter(adapter);
            }
//        });
//
//    }
    @Override
    protected void onStart() {
        adapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}