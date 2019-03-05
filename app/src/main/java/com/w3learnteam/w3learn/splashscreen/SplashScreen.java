package com.w3learnteam.w3learn.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.w3learnteam.w3learn.R;
import com.w3learnteam.w3learn.home_drawer;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        ImageView Splash = findViewById(R.id.splash);
        Animation Fade_In = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        Splash.startAnimation(Fade_In);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, home_drawer.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
