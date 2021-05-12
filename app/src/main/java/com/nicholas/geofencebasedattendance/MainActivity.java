package com.nicholas.geofencebasedattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN=5000;
    Handler handler;
    Animation topanim,bottomanim;
    ImageView imageOne,imageTwo;
    TextView motto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //animations
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //create hooks for the widgets
        imageOne=findViewById(R.id.welcomeTop);
        imageTwo=findViewById(R.id.welcomeBottom);
        motto=findViewById(R.id.textView);

        //setting animation
        imageOne.setAnimation(topanim);
        imageTwo.setAnimation(bottomanim);
        motto.setAnimation(bottomanim);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,ChoiceActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}