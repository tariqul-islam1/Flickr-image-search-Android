package com.example.retrofit_fastadapter.services;

import com.example.retrofit_fastadapter.models.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Tariqul.Islam on 4/30/17.
 */

public interface APIServices {

    @GET("posts")
    Observable<List<PostModel>> requestForPosts();

}
