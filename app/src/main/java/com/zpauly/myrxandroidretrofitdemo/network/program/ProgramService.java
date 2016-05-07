package com.zpauly.myrxandroidretrofitdemo.network.program;

import com.zpauly.myrxandroidretrofitdemo.entity.Program;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 16-5-5.
 */
public interface ProgramService {

    @GET("getProgram")
    Observable<Program> getPrograms(@Query("code") String code, @Query("date") String date, @Query("key") String key);
}
