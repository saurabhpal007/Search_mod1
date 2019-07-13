package com.pal.thirstymission.serach_mod1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class Adapbr extends RecyclerView.Adapter<Adapbr.MyViewHolder> {

    private List<Branches> branches;
    private Context context;

    public Adapbr(List<Branches> branches, Context context) {
        this.branches = branches;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.branch, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.checkbr.setText(branches.get(i).getBranch());
    }


    @Override
    public int getItemCount() {
        return branches.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkbr;
        public MyViewHolder(View itemView) {
            super(itemView);
            checkbr=itemView.findViewById(R.id.checkbr);
        }
    }
}
