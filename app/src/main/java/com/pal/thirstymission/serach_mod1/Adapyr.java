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

public class Adapyr  extends RecyclerView.Adapter<Adapyr.MyViewHolder>  {

    private List<Years> years;
    private Context context;
    String joined2=null;
    List<String> list2 = new ArrayList<String>();

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
    public void onBindViewHolder(@NonNull Adapyr.MyViewHolder myViewHolder, final int i) {
        myViewHolder.checkyr.setText(years.get(i).getYear());

        myViewHolder.checkyr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )

                {
                    list2.add("\""+years.get(i).getYear()+"\"");

                    joined2 = TextUtils.join(",", list2);
                    Log.i("qqqq",""+joined2);




                }

            }
        });

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
