package com.w3learnteam.w3learn.learn;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.w3learnteam.w3learn.R;
import com.w3learnteam.w3learn.learn.CSS;

import java.util.ArrayList;

public class CSS_Adapter extends RecyclerView.Adapter<CSS_Adapter.Css_Holder>{

    private Context context;
    private ArrayList<CSS> css_learn;

    public CSS_Adapter(Context context,ArrayList<CSS> css_learn){
        this.context = context;
        this.css_learn = css_learn;
    }

    @NonNull
    @Override
    public Css_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.css_item,parent,false);
        return new Css_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Css_Holder holder, int position) {
        holder.titleCSS.setText(css_learn.get(position).getTitleCSS());
        holder.descCSS.setText(css_learn.get(position).getDescCSS());
        holder.parentLaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,CSub_Item.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (css_learn != null) ? css_learn.size() : 0;
    }

    public class Css_Holder extends RecyclerView.ViewHolder{

        private TextView titleCSS,descCSS;
        private LinearLayout parentLaout;

        public Css_Holder(@NonNull View itemView) {
            super(itemView);
            titleCSS = (TextView) itemView.findViewById(R.id.titlecss);
            descCSS = (TextView) itemView.findViewById(R.id.desccss);
            parentLaout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
