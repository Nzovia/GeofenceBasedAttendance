package com.nicholas.geofencebasedattendance.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nicholas.geofencebasedattendance.R;
import com.nicholas.geofencebasedattendance.lecturer.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EnterClassActivity extends AppCompatActivity {

    private EditText name, adminnumber, realdate, realtime;
    Button attendClass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AttendanceModule objAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_class);

        name = findViewById(R.id.realname);
        adminnumber = findViewById(R.id.adminnumber);
        realdate = findViewById(R.id.realdate);
        realtime = findViewById(R.id.currentTime);

        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("AttendanceModule");

        // initializing our object
        // class variable.
        objAttendance = new AttendanceModule();

        SimpleDateFormat dateFormat= new SimpleDateFormat("EEE, d MMM YYYY", Locale.getDefault());
        SimpleDateFormat timeFormat =new SimpleDateFormat("HH:mm", Locale.getDefault());
        String date =dateFormat.format(Calendar.getInstance().getTime());
        String time =timeFormat.format(Calendar.getInstance().getTime());

        realdate.setText(date);
        realtime.setText(time);
        realdate.setEnabled(false);
        realtime.setEnabled(false);
         attendClass = findViewById(R.id.buttonattend);
         attendClass.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //get the texts input from the xml file
                 String id=databaseReference.push().getKey();
                 String studentName = name.getText().toString();
                 String studentAdminNumber = adminnumber.getText().toString();
                 String attendancedate = realdate.getText().toString();
                 String attendancetime = realtime.getText().toString();

                 if (TextUtils.isEmpty(studentName) && TextUtils.isEmpty(studentAdminNumber) &&
                         TextUtils.isEmpty(attendancedate) && TextUtils.isEmpty(attendancetime )) {
                     System.out.println("please fill all fields");
                 }else{
                     // else call the method to add 
                     // data to our database.
                     addDatatoFirebase(id, studentName, studentAdminNumber, attendancedate, attendancetime);
                 }
             }
         });

    }

    private void addDatatoFirebase(String id, String studentName, String studentAdminNumber,
                                   String attendancedate, String attendancetime) {
        objAttendance.setAttendanceName(studentName);
        objAttendance.setAttendanceNumber(studentAdminNumber);
        objAttendance.setAttendanceDate(attendancedate);
        objAttendance.setAttendanceTime(attendancetime);

        databaseReference.child(id).setValue(objAttendance).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), " successfully signed in", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getApplicationContext(), EnterClassActivity.class);
//                startActivity(intent);
            }

        });

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // inside the method of on Data change we are setting
//                // our object class to our database reference.
//                // data base reference will sends data to firebase.
//                databaseReference.setValue(objAttendance);
//
//                // after adding this data we are showing toast message.
//                Toast.makeText(EnterClassActivity.this, "attendance Successful", Toast.LENGTH_SHORT).show();
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            // if the data is not added or it is cancelled then
//            // we are displaying a failure toast message.
//            Toast.makeText(EnterClassActivity.this, "Attendance Failed " + error, Toast.LENGTH_SHORT).show();
//
//
//        }
//        });

}
}