package com.w3learnteam.w3learn.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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

public class HSub_Item extends AppCompatActivity {

    private SweetAlertDialog pDialog;
    private RecyclerView recyclerView;
    private ArrayList<HSub> list;
    private Hsub_Adapter hsub_adapter;
    private String TAG = CSS_Learn.class.getSimpleName();
    private FlowingDrawer mDrawer;

    private View navOpen;

    private static String url = "http://my-json-server.typicode.com/JulioRahman/MyGitGUIRepository/db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsub__item);

        recyclerView = (RecyclerView) findViewById(R.id.html_sub);

        navOpen = findViewById(R.id.navOpen);
        mDrawer = (FlowingDrawer) findViewById(R.id.flowingDrawer);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

        setupMenu();

        navOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openMenu(true);
            }
        });

        list = new ArrayList<>();
        new GetLearn().execute();
    }

    private class GetLearn extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new SweetAlertDialog(HSub_Item.this, SweetAlertDialog.PROGRESS_TYPE);
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

                        list.add(new HSub(title, desc));
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

            LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(HSub_Item.this,R.anim.recycleritemstart1);
            recyclerView.setLayoutAnimation(layoutAnimationController);

            hsub_adapter = new Hsub_Adapter(HSub_Item.this,list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HSub_Item.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(hsub_adapter);

            if (pDialog.isShowing()){
                pDialog.dismiss();
            }
            else {
                Toast.makeText(HSub_Item.this,"Error While Showing The Activity",Toast.LENGTH_SHORT).show();
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

