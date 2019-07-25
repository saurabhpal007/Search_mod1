package com.pal.thirstymission.serach_mod1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapbr extends RecyclerView.Adapter<Adapbr.MyViewHolder> {

    private List<Branches> branches;
    private Context context;
    String joined1=null;
    List<String> list1 = new ArrayList<String>();

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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.checkbr.setText(branches.get(i).getBranch());

        myViewHolder.checkbr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )

                {
                    list1.add(branches.get(i).getBranch());
                    joined1 = TextUtils.join("~", list1);

                }
                else{

                    list1.remove(branches.get(i).getBranch());
                    joined1 = TextUtils.join("~", list1);
                }
                Log.i("qqqqbr",""+joined1);
                if (context instanceof Getc) {
                    ((Getc) context).updatebrlist(joined1);
                }

            }
        });


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
