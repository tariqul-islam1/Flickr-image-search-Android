package com.example.retrofit_fastadapter.services;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tariqul.Islam on 4/30/17.
 */

public interface FlickrServices {

    @GET("photos_public.gne?format=json")
    Observable<String> requestForPosts(@Query("tags") String tag);

}
