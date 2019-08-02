package com.pal.thirstymission.serach_mod1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Users> users;
    private Context context;

    public Adapter(List<Users> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.name.setText("NAME:"+users.get(position).getName().toUpperCase());
       holder.id.setText("ID:"+String.valueOf(users.get(position).getId()));
        holder.cllg.setText("COLLEGE:"+users.get(position).getCollege());
        holder.br.setText("BRANCH:"+users.get(position).getBranch());
        holder.yr.setText("START YEAR:"+users.get(position).getYear());
      Log.i("checkkkk",""+String.valueOf(users.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,id,cllg,br,yr;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id=itemView.findViewById(R.id.ide);
            cllg=itemView.findViewById(R.id.cllgv);
            br=itemView.findViewById(R.id.brv);
            yr=itemView.findViewById(R.id.yrv);
        }
    }
}