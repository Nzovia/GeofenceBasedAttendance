package com.nicholas.geofencebasedattendance.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.R;
import com.nicholas.geofencebasedattendance.lecturer.activitiesModel;
import com.nicholas.geofencebasedattendance.lecturer.activitiesViewholder;

public class ClassPosts extends AppCompatActivity {
    private RecyclerView postsRecycler;
    DatabaseReference myDB;
    //FencesAdapter adapter;
    private FirebaseRecyclerAdapter<activitiesModel, activitiesViewholder> myadapter;
    FirebaseRecyclerOptions<activitiesModel> options;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_posts);
        postsRecycler = findViewById(R.id.postsrecycler);
        postsRecycler.setLayoutManager(new LinearLayoutManager(this));


        // calling a method to get data from
        // Firebase and set data to list view

        mauth = FirebaseAuth.getInstance();
        myDB = FirebaseDatabase.getInstance().getReference().child("posts").child( mauth.getCurrentUser().getUid());
        options = new FirebaseRecyclerOptions.Builder<activitiesModel>().setQuery(myDB,activitiesModel.class).build();


        myadapter = new FirebaseRecyclerAdapter<activitiesModel, activitiesViewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull activitiesViewholder holder, int position, @NonNull activitiesModel model) {


            }

            @NonNull
            @Override
            public activitiesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activitylist,parent,false);
                return  new activitiesViewholder(view);
            }
        };
        // Connecting Adapter class with the Recycler view*/
        postsRecycler.setAdapter(myadapter);
    }
    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        myadapter.startListening();
    }
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        myadapter.stopListening();
    }

}