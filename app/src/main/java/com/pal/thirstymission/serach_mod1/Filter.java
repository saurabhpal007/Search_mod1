package com.pal.thirstymission.serach_mod1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Filter extends AppCompatActivity {
    CheckBox check1, check2;
    Spinner spinner1, spinner2;
    private ApiInterface apiInterface;
    private List<Users> users;
    Button fil;
    String key1, key2, key3=null, key4=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filterlay);
        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        fil = findViewById(R.id.srch);


        fil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1.isChecked()) {
                    key3 = String.valueOf(check1.getText());
                }
                else{key3=null;}
                if (check2.isChecked()) {
                    key4 = String.valueOf(check2.getText());
                }
                else{key4=null;}
                key2 = String.valueOf(spinner1.getSelectedItem());
                key1 = String.valueOf(spinner2.getSelectedItem());
                fetchuser1(key1,key2,key3,key4);
            }
        });

    }
    public void fetchuser1(String key1, String key2, String key3, String key4) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Users>> call = apiInterface.getfc(key1, key2);
        Log.i("okk",""+key1+key2+key3+key4);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                users = response.body();
                Log.i("opppp", " "
                        + users.get(0).getId()+" "
                        +users.get(0).getName()+" "
                        +users.get(0).getCollege()+" "
                        +users.get(0).getBranch()+" "
                        +users.get(0).getYear());
                Log.i("pll",""+users);
                // adapter = new Adapter(users, MainActivity.this);
                // recyclerView.setAdapter(adapter);
                // adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(Filter.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}