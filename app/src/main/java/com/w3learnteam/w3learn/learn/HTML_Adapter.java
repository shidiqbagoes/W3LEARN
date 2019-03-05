package com.w3learnteam.w3learn.learn;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.w3learnteam.w3learn.R;
import com.w3learnteam.w3learn.quiz.QuizTime;

import java.util.ArrayList;

public class HTML_Adapter extends RecyclerView.Adapter<HTML_Adapter.Html_Holder>{

    private String TAG = QuizTime.class.getSimpleName();

    private SweetAlertDialog pDialog;

    private Context context;
    private ArrayList<HTML> html_learn;
    private int lastPosition = -1;

    public HTML_Adapter(Context context,ArrayList<HTML> html_learn){
        this.context = context;
        this.html_learn = html_learn;
    }

    @NonNull
    @Override
    public Html_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.html_item,parent,false);
        return new Html_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Html_Holder holder, final int position) {
        holder.titleHTML.setText(html_learn.get(position).getTitleHTML());
        holder.descHTML.setText(html_learn.get(position).getDescHTML());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,HSub_Item.class);
                context.startActivity(intent);
            }
        });

        //Animation animation = AnimationUtils.loadAnimation(context,
        //        (position > lastPosition) ? R.anim.adapter_topbottom : R.anim.adapter_bottomtop);
       // holder.itemView.startAnimation(animation);
       // lastPosition=position;
    }

    @Override
    public void onViewDetachedFromWindow(Html_Holder html_holder){
        super.onViewDetachedFromWindow(html_holder);
        html_holder.itemView.clearAnimation();
    }


    @Override
    public int getItemCount() {
        return (html_learn != null) ? html_learn.size() : 0;
    }



    public class Html_Holder extends RecyclerView.ViewHolder{

        private TextView titleHTML,descHTML;
        private LinearLayout parentLayout;

        public Html_Holder(@NonNull View itemView) {
            super(itemView);
            titleHTML = (TextView) itemView.findViewById(R.id.titlehtml);
            descHTML = (TextView) itemView.findViewById(R.id.deschtml);
            parentLayout = (LinearLayout) itemView.findViewById(R.id.parentLayout);
        }
    }
}
