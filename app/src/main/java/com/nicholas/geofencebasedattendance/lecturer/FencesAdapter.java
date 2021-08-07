package com.nicholas.geofencebasedattendance.lecturer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.nicholas.geofencebasedattendance.R;

import static android.widget.Toast.LENGTH_LONG;

public class FencesAdapter extends FirebaseRecyclerAdapter<FencesData,FencesAdapter.fencesViewHolder> {
    private Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} Toast.makeText(activity, "clicked on " +position, Toast.LENGTH_SHORT).show();that listens to a Firebase query. See
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

        holder.latitudecord.setText(model.getLatitude());
        holder.logititudecord.setText(model.getLogtitude());
        holder.coursename.setText(model.getUnittitle());
        holder.coursecode.setText(model.getUnitcode());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(v.getContext())
                        .setTitle("Attend Class")
                        .setIcon(R.drawable.ic_mark)
                        //.setBackground(R.drawable.alertdialog)
                        .setMessage("Make sure you attend class")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //do something here, this section calls the button
                                // that is going to perform a specific action
                                Toast.makeText(v.getContext(),"Attend Class Here", LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Dismiss", null)
                        .show();
//                AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
//                alertDialog.setTitle("attend class");
//                alertDialog.setMessage("make sure you attend class");
 //               alertDialog.show();

            }
        });
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
        ImageButton attendlecture;

        public fencesViewHolder(@NonNull View itemView) {
            super(itemView);
            //hooks on the attendance data layout
            latitudecord = itemView.findViewById(R.id.latitudeValue);
            logititudecord = itemView.findViewById(R.id.logititudevalue);
            coursename = itemView.findViewById(R.id.unitcreated);
            coursecode = itemView.findViewById(R.id.codecreated);
            attendlecture = itemView.findViewById(R.id.attenOrdelete);

        }
    }
    
}
