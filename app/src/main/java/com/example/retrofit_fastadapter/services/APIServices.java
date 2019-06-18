package com.example.retrofit_fastadapter.services;

import com.example.retrofit_fastadapter.models.FlickrModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tariqul.Islam on 4/30/17.
 */

public interface APIServices {

    @GET("photos_public.gne?format=json")
    Observable<FlickrModel> requestForPosts(@Query("tags") String tag);

}
