package com.w3learnteam.w3learn.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.shapeofview.ShapeOfView;
import com.w3learnteam.w3learn.R;
import com.w3learnteam.w3learn.api.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class QuizTime extends AppCompatActivity implements View.OnClickListener {

    private String TAG = QuizTime.class.getSimpleName();

    private SweetAlertDialog pDialog,ADialog;
    private TextView soal;
    View vInfo;
    private TextView jawa, jawb, jawc, jawd;
    private TextView Title, Subtitle1;
    private ShapeOfView BQ, A1, A2, A3, A4;

    // URL to get contacts JSON
    //private static String url = "http://192.168.43.170/!earn/api/soal/read.php";
    private static String url = "https://my-json-server.typicode.com/JulioRahman/myrepo/db";

    private boolean doubleBackToExit = false;
    ArrayList<String> soalList, jawList, randJawList;
    Random rand;
    String[] sJaw;
    int[] ans;
    String nama;
    int noSoal = 1, r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiztime);

        soalList = new ArrayList<>();
        jawList = new ArrayList<>();
        randJawList = new ArrayList<>();

        Title = (TextView) findViewById(R.id.title);
        Subtitle1 = (TextView) findViewById(R.id.subtitle);

        BQ = (ShapeOfView) findViewById(R.id.bgquiz);
        A1 = (ShapeOfView) findViewById(R.id.answer1);
        A2 = (ShapeOfView) findViewById(R.id.answer2);
        A3 = (ShapeOfView) findViewById(R.id.answer3);
        A4 = (ShapeOfView) findViewById(R.id.answer4);

        final Typeface montR = Typeface.createFromAsset(getAssets(), "fonts/montserratregular.ttf");
        Typeface monM = Typeface.createFromAsset(getAssets(), "fonts/montserratmedium.ttf");
        Typeface monL = Typeface.createFromAsset(getAssets(), "fonts/montserratlight.ttf");

        vInfo = findViewById(R.id.vInfo);

        vInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(QuizTime.this,SweetAlertDialog.NORMAL_TYPE);
                sweetAlertDialog.setTitleText("Rules Info");
                sweetAlertDialog.setContentText("1. Rules Pertama\t2. Rules Kedua\t3. Rules Ketiga");
                sweetAlertDialog.show();
                sweetAlertDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setVisibility(View.GONE);
            }
        });

        BQ.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    final TextView textView = new TextView(QuizTime.this);
                    textView.setTypeface(montR);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    ADialog = new SweetAlertDialog(QuizTime.this, SweetAlertDialog.NORMAL_TYPE);
                    ADialog.setCustomView(textView);
                    ADialog.show();

                    ADialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setVisibility(View.GONE);
                    textView.setText(soalList.get(noSoal-1));

                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ADialog.dismissWithAnimation();
                }
                return true;
            }
        });


        rand = new Random();

        sJaw = new String[10];
        ans = new int[]{0, 0};
        nama = getIntent().getStringExtra("nama");

        soal = findViewById(R.id.soal);
        jawa = findViewById(R.id.jawa);
        jawb = findViewById(R.id.jawb);
        jawc = findViewById(R.id.jawc);
        jawd = findViewById(R.id.jawd);

        new GetSoal().execute();

        jawa.setOnClickListener(this);
        jawb.setOnClickListener(this);
        jawc.setOnClickListener(this);
        jawd.setOnClickListener(this);

        Subtitle1.setText("Question Number 1");
    }

    @Override
    public void onClick(View v) {
        TextView button = (TextView) v;

        if (button.getText().equals(sJaw[noSoal-1])) {
            final SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Benar!");
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface d) {
                    dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setVisibility(View.GONE);
                }
            });
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface d) {
                    ans[0] += 1;
                    if (noSoal > 9) {
                        Intent i = new Intent(QuizTime.this, Quiz_Result.class);
                        i.putExtra("ans", ans);
                        i.putExtra("nama", nama);
                        startActivity(i);
                        finish();
                    } else {
                        noSoal++;
                        segarkan();
                    }
                }
            });
            dialog.show();
        } else {
            final SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Salah!");
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface d) {
                    dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setVisibility(View.GONE);
                }
            });
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface d) {
                    ans[1] += 1;
                    if (noSoal > 9) {
                        Intent i = new Intent(QuizTime.this, Quiz_Result.class);
                        i.putExtra("ans", ans);
                        i.putExtra("nama", nama);
                        startActivity(i);
                        finish();
                    } else {
                        noSoal++;
                        segarkan();
                    }
                }
            });
            dialog.show();
        }
    }

    void segarkan() {
        Subtitle1.setText("Question Number " + noSoal);
        soal.setText(soalList.get(noSoal-1));

        sJaw[noSoal-1] = jawList.get(0);

        for (int i = 4; i > 0; i--) {
            r = rand.nextInt(i);
            randJawList.add(jawList.get(r));
            jawList.remove(r);
        }

        jawa.setText(randJawList.get(((noSoal-1)*4)));
        jawb.setText(randJawList.get(((noSoal-1)*4)+1));
        jawc.setText(randJawList.get(((noSoal-1)*4)+2));
        jawd.setText(randJawList.get(((noSoal-1)*4)+3));

        BQ.setTranslationY(-300);
        A1.setTranslationX(560);
        A2.setTranslationX(560);
        A3.setTranslationX(560);
        A4.setTranslationX(560);

        BQ.setAlpha(0);
        A1.setAlpha(0);
        A2.setAlpha(0);
        A3.setAlpha(0);
        A4.setAlpha(0);

        BQ.animate().alpha(1).translationY(0).setDuration(400).setStartDelay(100);
        A1.animate().alpha(1).translationX(0).setDuration(400).setStartDelay(200);
        A2.animate().alpha(1).translationX(0).setDuration(400).setStartDelay(300);
        A3.animate().alpha(1).translationX(0).setDuration(400).setStartDelay(400);
        A4.animate().alpha(1).translationX(0).setDuration(400).setStartDelay(500);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExit) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(QuizTime.this, SweetAlertDialog.WARNING_TYPE);
            sweetAlertDialog.setTitleText("Are You Sure ?");
            sweetAlertDialog.setConfirmText("Exit");
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    QuizTime.super.onBackPressed();
                }
            });
            sweetAlertDialog.show();
            Button btn = (Button) sweetAlertDialog.findViewById(R.id.confirm_button);
            btn.setBackgroundResource(R.drawable.sweetbtn);
            return;
        }
        this.doubleBackToExit = true;
    }

    private class GetSoal extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new SweetAlertDialog(QuizTime.this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#54E2FF"));
            pDialog.setTitleText("Please Wait");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray soals = jsonObj.getJSONArray("soal");

                    // looping through All Contacts
                    for (int i = 0; i < soals.length(); i++) {
                        JSONObject s = soals.getJSONObject(i);

                        String soal = s.getString("soal");
                        String jawa = s.getString("jawa");
                        String jawb = s.getString("jawb");
                        String jawc = s.getString("jawc");
                        String jawd = s.getString("jawd");

                        soalList.add(soal);
                        jawList.add(jawa);
                        jawList.add(jawb);
                        jawList.add(jawc);
                        jawList.add(jawd);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing()){
                pDialog.dismiss();
                segarkan();
            }
            else {
                Toast.makeText(QuizTime.this,"Error While Showing The Activity",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
