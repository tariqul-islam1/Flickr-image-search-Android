package com.example.retrofit_fastadapter.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tariqul.Islam on 4/30/17.
 */

public class APIClient {

    private final static String BaseURL = "https://api.flickr.com/services/feeds/";
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().setLenient().create()
                    ))
                    .build();
        }
        return retrofit;
    }

    private APIClient() {
    }

}
