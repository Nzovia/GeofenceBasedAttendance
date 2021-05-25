package com.nicholas.geofencebasedattendance.lecturer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nicholas.geofencebasedattendance.R;
import com.nicholas.geofencebasedattendance.Student.studentItem;

import java.util.ArrayList;

public class RegisteredStudents extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textView;
    private SearchView searchView;

    DatabaseReference myDB;
    studentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fence);

        textView = findViewById(R.id.viewstudents);
        searchView = findViewById(R.id.searchItem);
        searchView.onActionViewExpanded(); //new Added line
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Registration Number");
        searchView.getQuery().toString();

        // Create a instance of the database and get
        // its reference

        recyclerView = findViewById(R.id.studentslist);
        //set the recyclerview linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // calling a method to get data from
        // Firebase and set data to list view
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        myDB = FirebaseDatabase.getInstance().getReference("myDB");
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

//        //searchview
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                firebaseUserSearch();
//            }
//        });
//
//    }
//    public void firebaseUserSearch(){
//        Query firebaseSearchQuery = myDB.orderByChild("regsitrationNo").startAt(String searchView).endAt(searchView + "\uf8ff");
//
//        FirebaseRecyclerAdapter<studentItem, studentAdapter.studentViewholder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<studentItem, studentAdapter.studentViewholder>(
//
//                studentItem.class,
//                R.layout.activity_create_fence,
//                studentAdapter.studentViewholder.class,
//                firebaseSearchQuery
//
//        ) {
//            @Override
//            protected void populateViewHolder(studentAdapter.studentViewholder viewHolder, studentItem model, int position) {
//
//
//                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getEmail(), model.getRegistrationNo());
//
//            }
//        };
//
//        recyclerView.setAdapter(firebaseRecyclerAdapter);
//
//    }

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