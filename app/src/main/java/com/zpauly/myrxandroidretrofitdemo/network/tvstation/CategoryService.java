package com.zpauly.myrxandroidretrofitdemo.network.tvstation;

import com.zpauly.myrxandroidretrofitdemo.entity.Category;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 16-5-5.
 */
public interface CategoryService {

    @GET("getCategory")
    Observable<Category> getCategorys(@Query("key") String key);
}
