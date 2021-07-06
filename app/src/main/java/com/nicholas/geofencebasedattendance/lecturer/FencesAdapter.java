package com.nicholas.geofencebasedattendance.lecturer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nicholas.geofencebasedattendance.R;

public class FencesAdapter extends FirebaseRecyclerAdapter<FencesData,FencesAdapter.fencesViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    //generate a constructor for the adapter
    public FencesAdapter(@NonNull FirebaseRecyclerOptions<FencesData> options) {
        super(options);
    }

    //here implement all the adapter-viewHolder methods
    @Override
    protected void onBindViewHolder(@NonNull fencesViewHolder holder, int position, @NonNull FencesData model) {

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//         View view= LayoutInflater.from(this).inflate(R.layout.authenticate_attendance,null);
//         builder.setView(view);
//         builder.create().show();

        holder.latitudecord.setText(model.getLatitude());
        holder.logititudecord.setText(model.getLogtitude());
        holder.coursename.setText(model.getUnittitle());
        holder.latitudecord.setText(model.getUnitcode());

    }


    @NonNull
    @Override
    public fencesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view

                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fenceitem, parent, false);
        return new FencesAdapter.fencesViewHolder(view);

    }

    public static class fencesViewHolder extends RecyclerView.ViewHolder {
        TextView latitudecord, logititudecord, coursename, coursecode;


        public fencesViewHolder(@NonNull View itemView) {
            super(itemView);
            //hooks on the attendance data layout
            latitudecord = itemView.findViewById(R.id.latvalue);
            logititudecord = itemView.findViewById(R.id.logititudevalue);
            coursename = itemView.findViewById(R.id.unitcreated);
            coursecode = itemView.findViewById(R.id.codecreated);


        }
    }
}
