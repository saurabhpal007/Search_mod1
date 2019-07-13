package com.pal.thirstymission.serach_mod1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

public class Adapyr  extends RecyclerView.Adapter<Adapyr.MyViewHolder>  {

    private List<Years> years;
    private Context context;

    public Adapyr(List<Years> years, Context context) {
        this.years = years;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapyr.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.year, viewGroup, false);
        return new Adapyr.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapyr.MyViewHolder myViewHolder, int i) {
        myViewHolder.checkyr.setText(years.get(i).getYear());

    }

    @Override
    public int getItemCount() {
        return years.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkyr;
        public MyViewHolder(View itemView) {
            super(itemView);
            checkyr=itemView.findViewById(R.id.checkyr);
        }
    }
}
