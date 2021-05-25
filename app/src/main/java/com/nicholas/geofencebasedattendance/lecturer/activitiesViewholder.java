package com.nicholas.geofencebasedattendance.lecturer;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicholas.geofencebasedattendance.R;

public class activitiesViewholder extends RecyclerView.ViewHolder{
    TextView title, description,time,comment;
    public activitiesViewholder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.posttitle);
        description = itemView.findViewById(R.id.postdescription);
        time = itemView.findViewById(R.id.posttime);
        comment = itemView.findViewById(R.id.postcomment);
    }
}
