package com.pal.thirstymission.serach_mod1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Getc extends AppCompatActivity {
    public List<Colleges> colleges;
    private ApiInterface apiInterface;
    private RecyclerView recyclerViewcllg;
    private RecyclerView.Adapter ad;
    private RecyclerView.LayoutManager layoutManager;
    Button src;
   // private Context context;
    public Getc()
    {
      //  this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter2);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewcllg= findViewById(R.id.recyclecllg);
        recyclerViewcllg.setLayoutManager(layoutManager);

        src=findViewById(R.id.srch2);
        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchcllg();
            }
        });

    }
    public void fetchcllg() {


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Colleges>> call = apiInterface.getcllg();
        call.enqueue(new Callback<List<Colleges>>() {
            @Override
            public void onResponse(Call<List<Colleges>> call, Response<List<Colleges>> response) {

                colleges = response.body();
                Log.i("ommmm", "u" + colleges.get(0).getCollege());
               ad = new Adapcllg(colleges, Getc.this);
               recyclerViewcllg.setAdapter(ad);
               ad.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Colleges>> call, Throwable t) {

            }
        });


    }
}
