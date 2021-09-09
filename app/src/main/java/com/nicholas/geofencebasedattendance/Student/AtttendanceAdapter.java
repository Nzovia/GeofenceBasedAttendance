package com.nicholas.geofencebasedattendance.Student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nicholas.geofencebasedattendance.R;
import com.nicholas.geofencebasedattendance.lecturer.CheckAttendance;

public class AtttendanceAdapter extends FirebaseRecyclerAdapter<AttendanceModule, AtttendanceAdapter.personViewholder> {

    public AtttendanceAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    @NonNull
    @Override
    public personViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendanceitem, parent, false);
        return new AtttendanceAdapter.personViewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull AtttendanceAdapter.personViewholder holder, int position,
                                    @NonNull AttendanceModule model) {
        holder.studname.setText(model.getAttendanceName());
        holder.adminnum.setText(model.getAttendanceNumber());
        holder.date.setText(model.getAttendanceDate());
        holder.time.setText(model.getAttendanceTime());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CheckAttendance checkAttendance = new CheckAttendance();
                checkAttendance.onDeleteItem(getItem(position));
                return true ;
            }
        });

    }

    public class personViewholder extends RecyclerView.ViewHolder {
        TextView studname, adminnum, date,time;
        public personViewholder(@NonNull View itemView) {
            super(itemView);
            studname = itemView.findViewById(R.id.studentname);
            adminnum = itemView.findViewById(R.id.adminnumber);
            date = itemView.findViewById(R.id.dateIn);
            time = itemView.findViewById(R.id.timeIn);
        }
    }
}
