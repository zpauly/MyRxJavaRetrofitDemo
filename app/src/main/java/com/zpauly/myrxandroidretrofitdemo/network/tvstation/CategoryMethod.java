package com.zpauly.myrxandroidretrofitdemo.network.tvstation;

import com.zpauly.myrxandroidretrofitdemo.Contants;
import com.zpauly.myrxandroidretrofitdemo.entity.Category;
import com.zpauly.myrxandroidretrofitdemo.network.RetrofitUtils;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-5-5.
 */
public class CategoryMethod {
    private static CategoryMethod instance = null;

    private Retrofit retrofit;

    private CategoryService service;

    private CategoryMethod() {
        retrofit = RetrofitUtils.initRetrofit(Contants.category_base_url);

        service = retrofit.create(CategoryService.class);
    }

    public static CategoryMethod getInstance() {
        if (instance == null) {
            synchronized (CategoryMethod.class) {
                instance = new CategoryMethod();
            }
        }
        return instance;
    }

    public void getCategorys(Observer<Category> categoryObserver) {
        service.getCategorys(Contants.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryObserver);
    }
}
