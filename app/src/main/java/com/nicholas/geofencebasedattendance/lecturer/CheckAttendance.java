package com.nicholas.geofencebasedattendance.lecturer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.R;
import com.nicholas.geofencebasedattendance.Student.AttendanceModule;
import com.nicholas.geofencebasedattendance.Student.AtttendanceAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CheckAttendance extends AppCompatActivity {
    private RecyclerView recyclerView;
    AtttendanceAdapter adapter; // Create Object of the Adapter class
    FirebaseDatabase firebaseDatabase;
    //object of the databases to which we refer data from
    DatabaseReference databaseReference;
    private EditText presentdate, presenttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_attendance);

        presentdate = findViewById(R.id.classdate);
        presenttime = findViewById(R.id.classtime);

        //setting default time and dates
        SimpleDateFormat dateFormat= new SimpleDateFormat("EEE, d MMM YYYY", Locale.getDefault());
        SimpleDateFormat timeFormat =new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String date =dateFormat.format(Calendar.getInstance().getTime());
        String time =timeFormat.format(Calendar.getInstance().getTime());

        presentdate.setText(date);
        presenttime.setText(time);
        presentdate.setEnabled(false);
        presenttime.setEnabled(false);
        // below line is used to get the
        // instance of our FIrebase database.
        databaseReference = FirebaseDatabase.getInstance().getReference("AttendanceModule");
        recyclerView = findViewById(R.id.attendancelist);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<AttendanceModule> options
                = new FirebaseRecyclerOptions.Builder<AttendanceModule>()
                .setQuery(databaseReference, AttendanceModule.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new AtttendanceAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
        //setting a divider vertically below each item
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void onDeleteItem(AttendanceModule item) {
        databaseReference = FirebaseDatabase.getInstance().getReference("attendanceData");
        databaseReference.removeValue();
    }

//    public void deleteItem() {
//        databaseReference.child(adapter.getId()).removeValue();
//        Toast.makeText(getApplicationContext(),"post deleted", Toast.LENGTH_LONG).show();
//    }
}