package com.pal.thirstymission.serach_mod1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    BroadcastReceiver receiver;
    private List<Users> users;

    private ApiInterface apiInterface;
    EditText searchedi;
    Button search;
    TextView nores;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;

    private RecyclerView recyclerViewfil;
    private RecyclerView.LayoutManager layoutManagerfil;
    private RecyclerView.Adapter adpfil;

    String passedItem="null";
    String passedItem2="null";
    String passedItem3="null";
    String cllgno="0",brno="0",yrno="0";


    FloatingActionButton fab;
    public static ArrayList<String> showf= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nores=findViewById(R.id.nores);
        searchedi = findViewById(R.id.searchedit);
        search = findViewById(R.id.searchbtn);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerViewfil = findViewById(R.id.recyclerViewfilter);
        recyclerViewfil.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        initBroadcastReceiver();
        fab = findViewById(R.id.floatingActionButton);

        //used to get all the contents from database



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showf.clear();
                Toast.makeText(MainActivity.this, "pressed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Getc.class));

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mmmm", ""+passedItem);
                Log.i("mmmm", ""+passedItem2);
                Log.i("mmmm", ""+passedItem3);

                if (!(searchedi.getText().length() == 0)) {
                    fetchfilter1(searchedi.getText().toString(),passedItem,passedItem2,passedItem3);
                   // fetchuser(searchedi.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Search Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initBroadcastReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                receiver=new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {

                        if (intent.getAction() != null && intent.getAction().equals("com.pal.thirstymission.serach_mod1")) {
                            passedItem = (String) intent.getStringExtra("passed_item");
                            passedItem2 = (String) intent.getStringExtra("passed_item2");
                            passedItem3 = (String) intent.getStringExtra("passed_item3");
                            cllgno = intent.getStringExtra("cllgno");
                            brno = intent.getStringExtra("brno");
                            yrno = intent.getStringExtra("yrno");
                            Toast.makeText(context, "Broadcast Received in Activity called " + cllgno, Toast.LENGTH_SHORT).show();
                            showf.add("College : "+cllgno);
                            showf.add("Branch : " + brno);
                            showf.add("Year : " + yrno);
                            adpfil = new Adapf(showf, MainActivity.this);
                            adpfil.notifyDataSetChanged();
                            recyclerViewfil.setAdapter(adpfil);

                        }



                    }
                };
            }
        };
    }

    public void fetchuser(String key) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Users>> call = apiInterface.getUs(key);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.body().equals("null")){Toast.makeText(MainActivity.this, "NO RESULT FOUND", Toast.LENGTH_LONG);}
                else {
                    nores.setVisibility(View.INVISIBLE);
                    users = response.body();
                    adapter = new Adapter(users, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "NO RESULT FOUND", Toast.LENGTH_LONG);
            }
        });
    }

    public void fetchfilter1(String key,String key0,String key2,String key3) {


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Users>> call = apiInterface.lo(key,key0,key2,key3);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                users=response.body();
                adapter = new Adapter(users, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.i("zzz",""+response.body());
               // if(response.body().equals(null)){nores.setVisibility(View.VISIBLE);}
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "NO RESULT FOUND", Toast.LENGTH_LONG);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("com.pal.thirstymission.serach_mod1"));
        

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }



}
