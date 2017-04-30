package com.example.retrofit_fastadapter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.retrofit_fastadapter.clients.APIClient;
import com.example.retrofit_fastadapter.models.PostModel;
import com.example.retrofit_fastadapter.services.APIServices;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler);

        getPosts();

    }

    private void getPosts() {
        APIServices apiService = APIClient.getInstance().create(APIServices.class);

        Call<List<PostModel>> call = apiService.getPosts();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                List<PostModel> posts = response.body();

                //init fast adapter
                FastItemAdapter<PostModel> fastAdapter = new FastItemAdapter<>();
                fastAdapter.add(posts);

                //fill the recycler view
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recycler.setLayoutManager(layoutManager);
                recycler.setAdapter(fastAdapter);

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}
