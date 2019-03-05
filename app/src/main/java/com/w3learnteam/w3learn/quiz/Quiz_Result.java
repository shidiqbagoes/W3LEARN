package com.w3learnteam.w3learn.quiz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.florent37.shapeofview.ShapeOfView;
import com.w3learnteam.w3learn.R;
import com.w3learnteam.w3learn.api.HttpHandler;

import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Quiz_Result extends AppCompatActivity {
    Animation ZoomIn, ZoomIn1, ZoomIn2;
    TextView TResult, TTitle, TTitle2, Tsub, R1, R2;
    ShapeOfView Star1, Star2, Star3;
    LinearLayout LN1, LN2;
    View Divider;
    ImageView Conveti;
    ProgressBar PB1, PB2;
    private SweetAlertDialog pDialog;

    private static String url = "http://192.168.43.170/!earn/api/score/insert.php";

    private int[] ans;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz__result);

        ans = getIntent().getIntArrayExtra("ans");
        nama = getIntent().getStringExtra("nama");

        TResult = (TextView) findViewById(R.id.tResult);
        TTitle = (TextView) findViewById(R.id.title1);
        TTitle2 = (TextView) findViewById(R.id.title2);
        Tsub = (TextView) findViewById(R.id.tsub);
        R1 = (TextView) findViewById(R.id.r1);
        R2 = (TextView) findViewById(R.id.r2);

        Star1 = (ShapeOfView) findViewById(R.id.star1);
        Star2 = (ShapeOfView) findViewById(R.id.star2);
        Star3 = (ShapeOfView) findViewById(R.id.star3);

        LN1 = (LinearLayout) findViewById(R.id.cans);
        LN2 = (LinearLayout) findViewById(R.id.wans);

        Divider = (View) findViewById(R.id.divide);

        PB1 = (ProgressBar) findViewById(R.id.pb1);
        PB2 = (ProgressBar) findViewById(R.id.pb2);

        Conveti = findViewById(R.id.conveti);

        Conveti.setTranslationY(-800);

        Conveti.animate().alpha(1).translationY(10).setDuration(1800).setStartDelay(0);

        if (ans[0] > 7) {

        } else if (ans[0] > 3) {
            Star3.setVisibility(View.GONE);
        } else {
            Star3.setVisibility(View.GONE);
            Star2.setVisibility(View.GONE);
        }

        ZoomIn = AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        ZoomIn1 = AnimationUtils.loadAnimation(this,R.anim.zoom_in1);
        ZoomIn2 = AnimationUtils.loadAnimation(this,R.anim.zoom_in2);

        TResult.setTranslationY(-250);
        TTitle.setTranslationX(-250);
        TTitle2.setTranslationX(-250);
        TTitle2.setTranslationX(-250);
        Tsub.setTranslationY(400);
        Divider.setTranslationY(400);
        LN1.setTranslationY(400);
        LN2.setTranslationY(400);
        Star1.startAnimation(ZoomIn);
        Star2.startAnimation(ZoomIn1);
        Star3.startAnimation(ZoomIn2);

        TResult.setAlpha(0);
        TTitle.setAlpha(0);
        TTitle2.setAlpha(0);
        Tsub.setAlpha(0);
        Divider.setAlpha(0);
        LN1.setAlpha(0);
        LN2.setAlpha(0);

        TResult.animate().alpha(1).translationY(0).setStartDelay(50).setDuration(800).start();
        TTitle.animate().alpha(1).translationX(0).setStartDelay(1000).setDuration(800).start();
        TTitle2.animate().alpha(1).translationX(0).setStartDelay(1200).setDuration(800).start();
        Tsub.animate().alpha(1).translationY(0).setStartDelay(1400).setDuration(800).start();
        Divider.animate().alpha(1).translationY(0).setStartDelay(1500).setDuration(800).start();
        LN1.animate().alpha(1).translationY(0).setStartDelay(1700).setDuration(800).start();
        LN2.animate().alpha(1).translationY(0).setStartDelay(1900).setDuration(800).start();

        url += "?nama=" + nama + "&skor=" + String.valueOf(ans[0]*10);
        new SetScore().execute();

        R1.setText(String.valueOf(ans[0]));
        R2.setText(String.valueOf(ans[1]));

        PB1.setProgress(ans[0]);
        PB2.setProgress(ans[1]);
    }

    private class SetScore extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new SweetAlertDialog(Quiz_Result.this);
            pDialog.getProgressHelper();
            pDialog.setTitleText("Please Wait");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            sh.makeServiceCall(url);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }
}
