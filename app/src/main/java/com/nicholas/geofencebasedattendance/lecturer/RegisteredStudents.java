package com.nicholas.geofencebasedattendance.lecturer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nicholas.geofencebasedattendance.R;
import com.nicholas.geofencebasedattendance.Student.studentItem;

public class RegisteredStudents extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textView;
    private EditText searchView;

    DatabaseReference myDB;
    studentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_students);

        textView = findViewById(R.id.viewstudents);
        searchView = findViewById(R.id.searchItem);
        searchView.setHint("Search Registration Number");

        // Create a instance of the database and get
        // its reference

        recyclerView = findViewById(R.id.studentslist);
        //set the recyclerview linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // calling a method to get data from
        // Firebase and set data to list view
        myDB = FirebaseDatabase.getInstance().getReference("myDB");

        LoadData("");
        //get the inputText data on the SearchView
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null){
                    LoadData(s.toString());
                }else{
                    LoadData("");
                }

            }
        });
   }

    private void LoadData(String data) {
        Query query=myDB.orderByChild("registrationNo").startAt(data).endAt(data+"\uf8ff");

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<studentItem> options
                = new FirebaseRecyclerOptions.Builder<studentItem>()
                .setQuery(query, studentItem.class)  // .setQuery(myDB, studentItem.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new studentAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
    }


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