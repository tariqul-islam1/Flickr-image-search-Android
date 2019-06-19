package com.example.retrofit_fastadapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.retrofit_fastadapter.clients.APIClient;
import com.example.retrofit_fastadapter.models.FlickrModel;
import com.example.retrofit_fastadapter.models.Item;
import com.example.retrofit_fastadapter.services.APIServices;
import com.example.retrofit_fastadapter.utils.DeviceData;
import com.google.gson.Gson;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    Context context;
    private ProgressDialog progressDialog;
    EditText searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        progressDialog = new ProgressDialog(context);
        recycler = findViewById(R.id.recycler);
        searchKey = findViewById(R.id.keyword);
        DeviceData.getInstance().setDisplay(getWindowManager().getDefaultDisplay());
    }

    private void getImages() {
        final APIServices apiService = APIClient.getInstance().create(APIServices.class);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String keyword = "nature";
        if (!searchKey.getText().toString().trim().isEmpty()){
            keyword = searchKey.getText().toString().trim();
        }

        apiService.requestForPosts(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                    String json = response.substring(15, response.length() - 1);

                    FlickrModel flickrModel = new Gson().fromJson(json, FlickrModel.class);
                    FastItemAdapter<Item> fastAdapter = new FastItemAdapter<>();
                    fastAdapter.add(flickrModel.items);

                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                    recycler.setLayoutManager(layoutManager);
                    recycler.setAdapter(fastAdapter);
                }, throwable -> {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    System.out.println(throwable.getMessage());
                }, () -> {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                });
    }

    public void searchAndUpdateUI(View view){
        getImages();
    }
}
