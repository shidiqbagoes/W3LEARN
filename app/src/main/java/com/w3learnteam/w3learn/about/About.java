package com.w3learnteam.w3learn.about;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.w3learnteam.w3learn.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class About extends AppCompatActivity {

    RecyclerView Creation;
    CreatorsAdapter creatorsAdapter;
    List<Creators> creatorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Creation = (RecyclerView) findViewById(R.id.creators);

        creatorsList = new ArrayList<>();
        creatorsList.add(
                new Creators(
                        "Shidiq Bagus",
                        "Android UI/UX Designer",
                        R.drawable.bagus
                )
        );

        creatorsList.add(
                new Creators(
                        "Julio Rahman",
                        "Android Backend Programmer",
                        R.drawable.julio
                )
        );

        creatorsList.add(
                new Creators(
                        "Redi Rusmana",
                        "Fullstack Web Developer",
                        R.drawable.redi
                )
        );
        creatorsList.add(
                new Creators(
                        "Sayid Mabrur",
                        "Graphic Designer",
                        R.drawable.sayid
                )
        );

        creatorsList.add(
                new Creators(
                        "Wahyu Rifaldi",
                        "Graphic Designer",
                        R.drawable.wahyu
                )
        );

        creatorsList.add(
                new Creators(
                        "Dewi Kurnia",
                        "Content Writer",
                        R.drawable.dewi
                )
        );

        creatorsList.add(
                new Creators(
                        "Dhiya Aqila",
                        "Content Writer",
                        R.drawable.dhiya
                )
        );

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL, false);

        Creation.setLayoutManager(linearLayoutManager);
        Creation.setHasFixedSize(true);

        creatorsAdapter = new CreatorsAdapter(this,creatorsList);
        Creation.setAdapter(creatorsAdapter);

        final SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(Creation);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 1ms = 100ms
                RecyclerView.ViewHolder viewHolderDefault = Creation.
                        findViewHolderForAdapterPosition(0);

                LinearLayout creatorsListDefault = viewHolderDefault.itemView.
                        findViewById(R.id.creatorlist);
                creatorsListDefault.animate().scaleY(0.96f).scaleX(0.96f).setDuration(550).
                        setInterpolator(new AccelerateInterpolator()).start();


            }
        }, 100);

       Creation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    View view = snapHelper.findSnapView(linearLayoutManager);
                    int pos = linearLayoutManager.getPosition(view);

                    RecyclerView.ViewHolder viewHolder =
                            Creation.findViewHolderForAdapterPosition(pos);

                    LinearLayout creatorlist = viewHolder.itemView.findViewById(R.id.creatorlist);
                    creatorlist.animate().scaleY(0.96f).scaleX(0.96f).setDuration(350).
                            setInterpolator(new AccelerateInterpolator()).start();

                }
                else {

                    View view = snapHelper.findSnapView(linearLayoutManager);
                    int pos = linearLayoutManager.getPosition(view);

                    RecyclerView.ViewHolder viewHolder =
                            Creation.findViewHolderForAdapterPosition(pos);

                    LinearLayout creatorlist = viewHolder.itemView.findViewById(R.id.creatorlist);
                    creatorlist.animate().scaleY(0.88f).scaleX(0.88f).
                            setInterpolator(new AccelerateInterpolator()).setDuration(350).start();

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
