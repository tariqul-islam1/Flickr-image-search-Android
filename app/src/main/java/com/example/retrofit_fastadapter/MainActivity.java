package com.example.retrofit_fastadapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.retrofit_fastadapter.clients.APIClient;
import com.example.retrofit_fastadapter.models.FlickrModel;
import com.example.retrofit_fastadapter.models.Item;
import com.example.retrofit_fastadapter.services.APIServices;
import com.example.retrofit_fastadapter.utils.DeviceData;
import com.google.gson.Gson;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    Context context;
    EditText searchKey;
    boolean isImageFitToScreen;
    ImageView fullScreenImg;
    String keyword;
    Spinner progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        recycler = findViewById(R.id.recycler);
        searchKey = findViewById(R.id.keyword);
        fullScreenImg = findViewById(R.id.fullScreenImg);
        progressBar = findViewById(R.id.progressBar);
        DeviceData.getInstance().setDisplay(getWindowManager().getDefaultDisplay());

        searchKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!keyword.isEmpty() && !keyword.substring(keyword.length() - 1).equals(" ")) {
                    getImages();
                }
            }
        });
    }

    private void getImages() {
        final APIServices apiService = APIClient.getInstance().create(APIServices.class);
        recycler.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Disposable disposable = apiService.requestForPosts(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(jsonString -> {
                    String json = jsonString.substring(15, jsonString.length() - 1);
                    FlickrModel flickrModel = new Gson().fromJson(json, FlickrModel.class);
                    return flickrModel.items;
                })
                .subscribe(items -> {
                    FastItemAdapter<Item> fastAdapter = new FastItemAdapter<>();
                    fastAdapter.add(items);
                    fastAdapter.withSelectable(true);
                    fastAdapter.withOnClickListener((v, adapter, item, position) -> {
                        fullScreenImg.setVisibility(View.VISIBLE);
                        String url = item.media.m.replace("_m.jpg", "_b.jpg");
                        Picasso.get().load(url).into(fullScreenImg);
                        System.out.println("image " + url);
                        return false;
                    });

                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                    recycler.setLayoutManager(layoutManager);
                    recycler.setAdapter(fastAdapter);
                    recycler.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }, throwable -> {
                    recycler.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    System.out.println(throwable.getMessage());
                }, () -> {
                    recycler.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                });
    }

    public void hideFullScreenImg(View view) {
        view.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (fullScreenImg.getVisibility() == View.VISIBLE) {
            fullScreenImg.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
