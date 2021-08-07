package com.nicholas.geofencebasedattendance.lecturer;

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
import com.nicholas.geofencebasedattendance.Student.studentItem;

import java.util.Objects;

import static java.util.Objects.*;

public class studentAdapter extends FirebaseRecyclerAdapter<studentItem, studentAdapter.studentViewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public studentAdapter(@NonNull FirebaseRecyclerOptions<studentItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull studentViewholder holder, int position, @NonNull studentItem model) {
        requireNonNull(holder).name.setText(model.getName());
        holder.regisrationNumber.setText(model.getRegistrationNo());
        holder.email.setText(model.getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"registered students", Toast.LENGTH_LONG).show();
            }
        });


    }

    @NonNull
    @Override
    public studentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new studentAdapter.studentViewholder(view);
        //return null;
    }

    public class studentViewholder extends RecyclerView.ViewHolder {

        //create variables to initialize
        TextView name, regisrationNumber, email;

        public studentViewholder(@NonNull View itemView) {
            super(itemView);

            //hooks on the studentitem
            name=itemView.findViewById(R.id.name);
            regisrationNumber=itemView.findViewById(R.id.registration);
            email=itemView.findViewById(R.id.Email);
        }
    }
}
