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
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Users> users;

    private ApiInterface apiInterface;
    EditText searchedi;
    Button search;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    String passedItem;
    String passedItem2;
    String passedItem3;
    int REQUEST_CODE=101;



    FloatingActionButton fab;


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


        fab = findViewById(R.id.floatingActionButton);

        //used to get all the contents from database



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            passedItem = (String) data.getExtras().get("passed_item");
            passedItem2 = (String) data.getExtras().get("passed_item2");
            passedItem3 = (String) data.getExtras().get("passed_item3");
        }
    }




}
