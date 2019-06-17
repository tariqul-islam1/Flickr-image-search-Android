package com.example.retrofit_fastadapter.clients;

import com.example.retrofit_fastadapter.models.PostModel;
import com.example.retrofit_fastadapter.services.APIServices;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tariqul.Islam on 4/30/17.
 */

public class APIClient {

    private final static String BaseURL = "http://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private APIClient() {
    }

}
