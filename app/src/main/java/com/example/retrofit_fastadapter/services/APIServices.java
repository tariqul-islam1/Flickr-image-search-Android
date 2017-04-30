package com.example.retrofit_fastadapter.services;

import com.example.retrofit_fastadapter.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tariqul.Islam on 4/30/17.
 */

public interface APIServices {

    @GET("posts")
    Call<List<PostModel>> getPosts();

}
