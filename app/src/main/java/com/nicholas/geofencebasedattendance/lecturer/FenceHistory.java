package com.nicholas.geofencebasedattendance.lecturer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.R;


public class FenceHistory extends AppCompatActivity {
    private RecyclerView history_recycler;
    DatabaseReference mydatabases;
    FencesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fence_history);
        history_recycler = findViewById(R.id.fenceslist);

        history_recycler.setLayoutManager(new LinearLayoutManager(this));
        // calling a method to get data from
        // Firebase and set data to list view
        mydatabases = FirebaseDatabase.getInstance().getReference().child("ClassFences");


        FirebaseRecyclerOptions<FencesData> options
                = new FirebaseRecyclerOptions.Builder<FencesData>()
                .setQuery(mydatabases, FencesData.class)  // .setQuery(myDB, studentItem.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new FencesAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        history_recycler.setAdapter(adapter);
    }
    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}