package com.pal.thirstymission.serach_mod1;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
  Filter filter;
    private List<Users> users;
    private ApiInterface apiInterface;
    EditText searchedi;
    Button search;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
   // private Spinner spinner1;
  FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchedi=findViewById(R.id.searchedit);
        search=findViewById(R.id.searchbtn);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
       // spinner1=findViewById(R.id.spinner1);
       fab=findViewById(R.id.floatingActionButton);
        //used to get all the contents from database
      //  fetchuser("sam");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Filter.class);
                startActivity(intent);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("opqs","u"+searchedi.getText().toString());
                if(!(searchedi.getText().length()==0)) {
                    fetchuser(searchedi.getText().toString());
                }
                else
                {

                    Toast.makeText(MainActivity.this, "Search Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void fetchuser(String key){

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Users>> call = apiInterface.getUs(key);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                users = response.body();
                Log.i("opppp",""+users.get(0).getId());
                adapter = new Adapter(users, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }




}
