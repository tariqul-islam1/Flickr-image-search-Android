package com.example.retrofit_fastadapter.clients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tariqul.Islam on 4/30/17.
 */

public class APIClient {

    private final static String BaseURL = "http://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
    private APIClient(){}

}
