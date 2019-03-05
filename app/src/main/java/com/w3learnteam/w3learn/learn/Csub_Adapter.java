package com.w3learnteam.w3learn.learn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.w3learnteam.w3learn.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Csub_Adapter extends RecyclerView.Adapter<Csub_Adapter.CSubHolder> {

    private Context context;
    private ArrayList<Csub> csubs;


    public  Csub_Adapter(Context context,ArrayList<Csub> csubs){
        this.context = context;
        this.csubs = csubs;
    }

    @NonNull
    @Override
    public CSubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.csub_item,parent,false);
        return new Csub_Adapter.CSubHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CSubHolder holder, int position) {
        holder.titlecsub.setText(csubs.get(position).getCsubitem());
        holder.desccsub.setText(csubs.get(position).getCsubdesc());
        holder.ParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,List_Code.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (csubs != null) ? csubs.size() : 0;
    }

    public class CSubHolder extends RecyclerView.ViewHolder {

        private TextView titlecsub,desccsub;
        private LinearLayout ParentLayout;

        public CSubHolder(@NonNull View itemView) {
            super(itemView);
            titlecsub = itemView.findViewById(R.id.csubitem);
            desccsub = itemView.findViewById(R.id.cdescitem);
            ParentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
