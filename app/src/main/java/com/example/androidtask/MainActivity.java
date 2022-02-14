package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  List<Model> herosList = new ArrayList<Model>();
    private Adapter adapter;
    private String TAG = "MainActivity";
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressbar = (ProgressBar)findViewById(R.id.progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter( MainActivity.this);
        recyclerView.setAdapter(adapter);

        ApiInterface apiInterface = Api.getClient().create(ApiInterface.class);
        progressbar.setVisibility(View.VISIBLE);

        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

        if (!connected){
            Toast.makeText(this, "turn on internet", Toast.LENGTH_LONG).show();
            progressbar.setVisibility(View.GONE);
            return;
        }
        Call<ResponseData<List<Model>>> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<ResponseData<List<Model>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<Model>>> call, Response<ResponseData<List<Model>>> response) {

               List<Model> data = response.body().data;
               herosList.clear();
               herosList.addAll(data);
               adapter.setData(data);
               progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseData<List<Model>>> call, Throwable t) {
                if (BuildConfig.DEBUG){
                    Log.i(TAG,t.toString());
                }
                progressbar.setVisibility(View.GONE);
                call.cancel();
            }
        });

    }


}