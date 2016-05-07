package com.zpauly.myrxandroidretrofitdemo.network;

import com.zpauly.myrxandroidretrofitdemo.Contants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 16-5-5.
 */
public class RetrofitUtils {
    public static Retrofit initRetrofit(String baseUrl) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Contants.default_timeout, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        return retrofit;
    }
}
