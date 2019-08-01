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

import java.util.ArrayList;
import java.util.List;

public class Adapcllg extends RecyclerView.Adapter<Adapcllg.MyViewHolder> {



    private List<Colleges> colleges;


    private Context context;
    String joined=null;
    List<String> list = new ArrayList<String>();

    public Adapcllg(List<Colleges> colleges, Context context) {
        this.colleges = colleges;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cllg, viewGroup, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.checkcllg.setText(colleges.get(i).getCollege());

      myViewHolder.checkcllg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )

                {
                     list.add(colleges.get(i).getCollege());
                      joined = TextUtils.join("~", list);

                }
                else{

                   list.remove(colleges.get(i).getCollege());
                   joined = TextUtils.join("~", list);
                }
                Log.i("qqqq",""+joined);
                if (context instanceof Getc) {
                    ((Getc) context).updatecllglist(joined,list.size());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return colleges.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkcllg;
        public MyViewHolder(View itemView) {
            super(itemView);
            checkcllg=itemView.findViewById(R.id.checkcllg);
        }
    }

}
