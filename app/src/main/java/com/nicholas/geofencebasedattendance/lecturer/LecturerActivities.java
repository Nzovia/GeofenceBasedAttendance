package com.nicholas.geofencebasedattendance.lecturer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nicholas.geofencebasedattendance.R;

public class LecturerActivities extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private FirebaseAuth mauth;
    DatabaseReference myDB;
    private FirebaseRecyclerAdapter<activitiesModel,activitiesViewholder> myadapter;
    FirebaseRecyclerOptions<activitiesModel> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_activities);
        recyclerView=findViewById(R.id.postlist);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        //mydb= FirebaseDatabase.getInstance().getReference();
        mauth = FirebaseAuth.getInstance();
        myDB = FirebaseDatabase.getInstance().getReference().child("posts").child( mauth.getCurrentUser().getUid());


        //creating an onclick listener that calls the dialogbox to display
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        //set a code to display recyclerview linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        options = new FirebaseRecyclerOptions.Builder<activitiesModel>().setQuery(myDB,activitiesModel.class).build();
        myadapter=new FirebaseRecyclerAdapter<activitiesModel, activitiesViewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull activitiesViewholder myholder, int position, @NonNull activitiesModel model) {


                myholder.editbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       updatePost(model.getId(),model.getTitle(),model.getDescription(),model.getTime(),model.getComment());

                    }
                });
                myholder.deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDB.child(model.getId()).removeValue();
                        Toast.makeText(getApplicationContext(),"post deleted", Toast.LENGTH_LONG).show();
                    }
                });
                myholder.title.setText(model.getTitle());
                myholder.description.setText(model.getDescription());
                myholder.time.setText(model.getTime());
                myholder.comment.setText(model.getComment());
            }

            @NonNull
            @Override
            public activitiesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activitylist,parent,false);
                return  new activitiesViewholder(view);
            }
        };
        myadapter.startListening();
        recyclerView.setAdapter(myadapter);
    }


    private void showDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view= LayoutInflater.from(this).inflate(R.layout.activitiesdialog,null);
        final AlertDialog dialog=builder.create();
        dialog.setView(view);

        EditText editText=view.findViewById(R.id.titleText);
        EditText editText1=view.findViewById(R.id.descriptionText);
        EditText editText2=view.findViewById(R.id.timeText);
        EditText editText3=view.findViewById(R.id.commentText);
        Button addbtn=view.findViewById(R.id.addPost);

        //code for closing the dialog once the close button is clicked
        ImageButton imageButton=view.findViewById(R.id.closePost);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=myDB.push().getKey();
                String title=editText.getText().toString();
                String description = editText1.getText().toString();
                String time=editText2.getText().toString();
                String comment = editText3.getText().toString();
                //first check the notes area is empty and if so then throw an error
                if(TextUtils.isEmpty(description)){
                    editText1.setError("Can not add an empty post");
                }else if(TextUtils.isEmpty(title)){
                    editText.setError("add post title");
                }
                else if(TextUtils.isEmpty(time)){
                    editText2.setError("time updated");
                }
                else if(TextUtils.isEmpty(comment)){
                    editText3.setError("empty comment");
                }
                else{
                    activitiesModel model=new activitiesModel(id,title,description,time,comment);
                    myDB.child(id).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "post successfully added", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }

                    });
                }

            }
        });
        dialog.show();
    }
    //the followng function tells the app to start fetching data from the database

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    //update the post by the lecturer
    private void updatePost(String id, String title, String description, String time, String comment) {
        android.app.AlertDialog.Builder mydialog = new android.app.AlertDialog.Builder(LecturerActivities.this);
        LayoutInflater inflater = LayoutInflater.from(LecturerActivities.this);
        View mview = inflater.inflate(R.layout.activitiesdialog,null);
        final android.app.AlertDialog dialog = mydialog.create();
        dialog.setView(mview);

        // hooks to the dialog widgets
        final EditText titlePost =  mview.findViewById(R.id.titleText);
        final EditText descriptionPost = mview.findViewById(R.id.descriptionText);
        final EditText timePost =  mview.findViewById(R.id.timeText);
        final EditText commentPost = mview.findViewById(R.id.commentText);
        Button update = mview.findViewById(R.id.addPost);
        update.setText("UPDATE");
        ImageButton close=mview.findViewById(R.id.closePost);

        titlePost.setText(title);
        descriptionPost.setText(description);
        timePost.setText(time);
        commentPost.setText(comment);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activitiesModel model = new activitiesModel(id,titlePost.getText().toString(),descriptionPost.getText().toString(),
                        timePost.getText().toString(),commentPost.getText().toString().toString());
                myDB.child(id).setValue(model);
                dialog.dismiss();

                Toast.makeText(getApplicationContext(),"post updated", Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

}