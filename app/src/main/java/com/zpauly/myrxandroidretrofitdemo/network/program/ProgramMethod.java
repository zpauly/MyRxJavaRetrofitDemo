package com.zpauly.myrxandroidretrofitdemo.network.program;

import com.zpauly.myrxandroidretrofitdemo.Contants;
import com.zpauly.myrxandroidretrofitdemo.entity.Program;
import com.zpauly.myrxandroidretrofitdemo.network.RetrofitUtils;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-5-5.
 */
public class ProgramMethod {
    private static ProgramMethod instance = null;

    private Retrofit retrofit;

    private ProgramService service;

    private ProgramMethod() {
        retrofit = RetrofitUtils.initRetrofit(Contants.program_base_url);

        service = retrofit.create(ProgramService.class);
    }

    public static ProgramMethod getInstance() {
        if (instance == null) {
            synchronized (ProgramMethod.class) {
                if (instance == null) {
                    instance = new ProgramMethod();
                }
            }
        }
        return instance;
    }

    public void getPrograms(Observer<Program> programObserver, String code, String date) {
        service.getPrograms(code, date, Contants.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(programObserver);
    }
}
