package com.w3learnteam.w3learn.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.w3learnteam.w3learn.MenuListFragments;
import com.w3learnteam.w3learn.R;
import com.w3learnteam.w3learn.api.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CSS_Learn extends AppCompatActivity {

    private String TAG = CSS_Learn.class.getSimpleName();

    private SweetAlertDialog pDialog;

    private RecyclerView C_Subject;
    private ArrayList<CSS> list;
    private CSS_Adapter cssAdapter;

    // URL to get contacts JSON
    //private static String url = "http://192.168.43.170/!earn/api/learn/read_css.php";
    private static String url = "http://my-json-server.typicode.com/JulioRahman/MyGitGUIRepository/db";

    TextView CTitle,CSub;
    private FlowingDrawer mDrawer;
    private View navOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_css__learn);

        CTitle = (TextView) findViewById(R.id.ctitle);
        CSub = (TextView) findViewById(R.id.csubtitle);

        list = new ArrayList<>();
        new GetLearn().execute();

        C_Subject = (RecyclerView) findViewById(R.id.css_subject);

        CTitle.setAlpha(0);
        CSub.setAlpha(0);

        mDrawer = (FlowingDrawer) findViewById(R.id.flowingDrawer);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        navOpen = findViewById(R.id.navOpen);
        navOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openMenu(true);
            }
        });

        setupMenu();
    }

    private class GetLearn extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new SweetAlertDialog(CSS_Learn.this, SweetAlertDialog.PROGRESS_TYPE);
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
                    JSONArray soals = jsonObj.getJSONArray("css");

                    // looping through All Contacts
                    for (int i = 0; i < soals.length(); i++) {
                        JSONObject s = soals.getJSONObject(i);

                        String title = s.getString("title");
                        String desc = s.getString("desc");

                        list.add(new CSS(title,desc));
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

            CTitle.setTranslationX(-300);
            CSub.setTranslationX(300);


            CTitle.animate().alpha(1).translationX(0).setDuration(500).setStartDelay(300);
            CSub.animate().alpha(1).translationX(0).setDuration(500).setStartDelay(600);

            cssAdapter = new CSS_Adapter(CSS_Learn.this,list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CSS_Learn.this);
            C_Subject.setLayoutManager(layoutManager);
            C_Subject.setAdapter(cssAdapter);

            LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(CSS_Learn.this,R.anim.recycleritemstart);
            C_Subject.setLayoutAnimation(layoutAnimationController);

            if (pDialog.isShowing()){
                pDialog.dismiss();
            }
            else {
                Toast.makeText(CSS_Learn.this,"Error While Showing The Activity",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragments mMenuFragment = (MenuListFragments) fm.findFragmentById(R.id.container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragments();
            fm.beginTransaction().add(R.id.container_menu, mMenuFragment).commit();
        }
    }
    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
