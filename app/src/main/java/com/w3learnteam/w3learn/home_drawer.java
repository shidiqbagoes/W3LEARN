package com.w3learnteam.w3learn;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.github.florent37.shapeofview.ShapeOfView;
import com.w3learnteam.w3learn.about.About;
import com.w3learnteam.w3learn.learn.CSS_Learn;
import com.w3learnteam.w3learn.learn.HTML_Learn;
import com.w3learnteam.w3learn.quiz.QuizRules;

public class home_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView Title1,Title2,Sub,Cn,Cn1,Cn2;
    ShapeOfView HTML,CSS,QUIZ;
    View navOpen;
    Animation Fadein,Fadein1,Fadein2,
              Move1,Move2,Move3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Title1 = (TextView) findViewById(R.id.htitle);
        Title2 = (TextView) findViewById(R.id.htitle2);
        Sub = (TextView) findViewById(R.id.hsubtitle);
        Cn = (TextView) findViewById(R.id.cname);
        Cn1 = (TextView) findViewById(R.id.cname1);
        Cn2 = (TextView) findViewById(R.id.cname2);
        HTML = (ShapeOfView) findViewById(R.id.html);
        CSS = (ShapeOfView) findViewById(R.id.css);
        QUIZ = (ShapeOfView) findViewById(R.id.quiz);
        navOpen = findViewById(R.id.navOpen);

        Fadein = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        Fadein1 = AnimationUtils.loadAnimation(this,R.anim.fade_in1);
        Fadein2 = AnimationUtils.loadAnimation(this,R.anim.fade_in2);

        Move1 = AnimationUtils.loadAnimation(this, R.anim.move_left1);
        Move2 = AnimationUtils.loadAnimation(this, R.anim.move_left2);
        Move3 = AnimationUtils.loadAnimation(this, R.anim.move_left3);

        HTML.setTranslationX(400);
        CSS.setTranslationX(400);
        QUIZ.setTranslationX(400);
        Cn.setTranslationX(400);
        Cn1.setTranslationX(400);
        Cn2.setTranslationX(400);
        Title1.setTranslationX(100);
        Title2.setTranslationX(-100);
        Sub.setTranslationY(100);

        HTML.setAlpha(0);
        CSS.setAlpha(0);
        QUIZ.setAlpha(0);
        Cn.setAlpha(0);
        Cn1.setAlpha(0);
        Cn2.setAlpha(0);
        Title1.setAlpha(0);
        Title2.setAlpha(0);
        Sub.setAlpha(0);

        HTML.animate().translationX(0).alpha(1).setStartDelay(300).setDuration(800).start();
        CSS.animate().translationX(0).alpha(1).setStartDelay(500).setDuration(800).start();
        QUIZ.animate().translationX(0).alpha(1).setStartDelay(700).setDuration(800).start();
        Cn.animate().translationX(0).alpha(1).setStartDelay(200).setDuration(800).start();
        Cn1.animate().translationX(0).alpha(1).setStartDelay(400).setDuration(800).start();
        Cn2.animate().translationX(0).alpha(1).setStartDelay(600).setDuration(800).start();
        Title1.animate().translationX(0).alpha(1).setStartDelay(100).setDuration(800).start();
        Title2.animate().translationX(0).alpha(1).setStartDelay(300).setDuration(800).start();
        Sub.animate().translationY(0).alpha(1).setStartDelay(500).setDuration(800).start();

        HTML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_drawer.this, HTML_Learn.class);
                startActivity(intent);
            }
        });

        CSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home_drawer.this, CSS_Learn.class);
                startActivity(intent);
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navOpen.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
             }
         });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        QUIZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home_drawer.this, QuizRules.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            navOpen.animate().alpha(1).start();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
        } else if (id == R.id.nav_game) {
            Intent intent = new Intent(this, QuizRules.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(this, FlownDraw.class);
            startActivity(intent);
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;




    }
}
