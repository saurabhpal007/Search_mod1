package com.pal.thirstymission.serach_mod1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Getc extends AppCompatActivity {
    public List<Colleges> colleges;
    public List<Branches> branches;

    public List<Years> years;
    private ApiInterface apiInterface;
    private RecyclerView recyclerViewcllg;
    private RecyclerView.Adapter ad;
    private RecyclerView.LayoutManager layoutManager;
    Button src;
    private static List<Users> usersFilter;
   private RecyclerView recyclerViewbr;
    private RecyclerView.Adapter adb;
    private RecyclerView.LayoutManager layoutManager2;

    private RecyclerView recyclerViewyr;
    private RecyclerView.Adapter ady;
    private RecyclerView.LayoutManager layoutManager3;

   public static String cllg;
    public static String br;
    public static String yr;


    public static int cllga=0,bra=0,yra=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter2);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewcllg= findViewById(R.id.recyclecllg);
        recyclerViewcllg.setLayoutManager(layoutManager);

        layoutManager2 = new LinearLayoutManager(this);
        recyclerViewbr= findViewById(R.id.recyclebr);
        recyclerViewbr.setLayoutManager(layoutManager2);

        layoutManager3 = new LinearLayoutManager(this);
        recyclerViewyr= findViewById(R.id.recyclecyr);
        recyclerViewyr.setLayoutManager(layoutManager3);

        fetchcllg();
        fetchbr();
        fetchyr();

        src=findViewById(R.id.srch2);
        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.pal.thirstymission.serach_mod1");
                intent.putExtra("cllgno", ""+cllga);
                intent.putExtra("brno", ""+bra);
                intent.putExtra("yrno", ""+yra);
                sendBroadcast(intent);

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
    public void fetchbr() {


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Branches>> call = apiInterface.getbr();
        call.enqueue(new Callback<List<Branches>>() {
            @Override
            public void onResponse(Call<List<Branches>> call, Response<List<Branches>> response) {

                branches = response.body();
                Log.i("ommmm", "u" + branches.get(0).getBranch());
                adb = new Adapbr(branches, Getc.this);
                recyclerViewbr.setAdapter(adb);
                adb.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Branches>> call, Throwable t) {

            }
        });


    }
    public void fetchyr() {


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Years>> call = apiInterface.getyr();
        call.enqueue(new Callback<List<Years>>() {
            @Override
            public void onResponse(Call<List<Years>> call, Response<List<Years>> response) {

                years = response.body();
                Log.i("oyy", "u" + years.get(0).getYear());
                ady = new Adapyr(years, Getc.this);
                recyclerViewyr.setAdapter(ady);
                ady.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Years>> call, Throwable t) {
                Toast.makeText(Getc.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();

            }
        });


    }


    public void updatecllglist(String s,int a) {
        cllg=s;
        cllga=a;


    }

    public void updatebrlist(String s,int a) {
    br=s;
    bra=a;

    }
    public void updateyrlist(String s, int a) {
      yr=s;
      yra=a;

    }
    @Override
    public void finish() {
        Intent returnIntent = new Intent();

        returnIntent.putExtra("passed_item", ""+cllg);
        returnIntent.putExtra("passed_item2", ""+br);
        returnIntent.putExtra("passed_item3", ""+yr);
        setResult(RESULT_OK, returnIntent); //By not passing the intent in the result, the calling activity will get null data.
        super.finish();
    }
}
