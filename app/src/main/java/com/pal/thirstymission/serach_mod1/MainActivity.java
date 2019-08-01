package com.pal.thirstymission.serach_mod1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;

    private RecyclerView recyclerViewfil;
    private RecyclerView.LayoutManager layoutManagerfil;
    private RecyclerView.Adapter adpfil;

    String passedItem=null;
    String passedItem2=null;
    String passedItem3=null;
    int REQUEST_CODE=101;

    IntentFilter filter;
    Boolean myReceiverIsRegistered = false;

    FloatingActionButton fab;
    public static ArrayList<String> showf= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchedi = findViewById(R.id.searchedit);
        search = findViewById(R.id.searchbtn);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerViewfil = findViewById(R.id.recyclerViewfilter);
        recyclerViewfil.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        fab = findViewById(R.id.floatingActionButton);

        //used to get all the contents from database



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showf.clear();
                Toast.makeText(MainActivity.this, "pressed", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(MainActivity.this, Getc.class), REQUEST_CODE);


            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Log.i("mmmm", ""+passedItem);
                Log.i("mmmm", ""+passedItem2);
                Log.i("mmmm", ""+passedItem3);
                fetchfilter1(searchedi.getText().toString(),passedItem,passedItem2,passedItem3);
                if (!(searchedi.getText().length() == 0)) {
                    fetchuser(searchedi.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Search Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void fetchuser(String key) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Users>> call = apiInterface.getUs(key);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                users = response.body();
                Log.i("opppp", "" + users.get(0).getYear());
                adapter = new Adapter(users, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
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
                Log.i("otttt", "u"+users.get(0).getName()
                        +""+users.get(0).getCollege()
                        +""+users.get(0).getBranch()
                        +""+users.get(0).getYear());
                Log.i("zz",""+users);
                adapter = new Adapter(users, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "NO RESULT FOUND", Toast.LENGTH_LONG);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            passedItem = (String) data.getExtras().get("passed_item");
            passedItem2 = (String) data.getExtras().get("passed_item2");
            passedItem3 = (String) data.getExtras().get("passed_item3");
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String cllgno = intent.getStringExtra("cllgno");
                String brno = intent.getStringExtra("brno");
                String yrno = intent.getStringExtra("yrno");
                Toast.makeText(context,"Broadcast Received in Activity called "+cllgno,Toast.LENGTH_SHORT).show();
                showf.add("College : "+cllgno);
                showf.add("Branch : "+brno);
                showf.add("Year : "+yrno);

                adpfil = new Adapf(showf,MainActivity.this);
                recyclerViewfil.setAdapter(adpfil);
                adpfil.notifyDataSetChanged();



            }
        };

        if (!myReceiverIsRegistered) {
            myReceiverIsRegistered = true;
            filter = new IntentFilter();
            filter.addAction("com.pal.thirstymission.serach_mod1");
            registerReceiver(receiver, filter);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(receiver!=null)
        {
            unregisterReceiver(receiver);
        }
    }



}
