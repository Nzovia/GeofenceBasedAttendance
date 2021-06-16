package com.nicholas.geofencebasedattendance.moreadapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nicholas.geofencebasedattendance.moreDataModels.FenceData;

public class FencesAdapter extends FirebaseRecyclerAdapter <FenceData,FencesAdapter.fencesViewHolder> {

  //generate a constructor for the adapter
    public FencesAdapter(@NonNull FirebaseRecyclerOptions<FenceData> options) {
        super(options);
    }
    //here implement all the adapter-viewHolder methods
    @Override
    protected void onBindViewHolder(@NonNull fencesViewHolder holder, int position, @NonNull FenceData model) {

    }

    @NonNull
    @Override
    public fencesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public class fencesViewHolder extends RecyclerView.ViewHolder {
        public fencesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
