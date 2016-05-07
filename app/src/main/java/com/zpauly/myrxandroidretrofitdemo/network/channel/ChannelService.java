package com.zpauly.myrxandroidretrofitdemo.network.channel;

import com.zpauly.myrxandroidretrofitdemo.entity.Channel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 16-5-5.
 */
public interface ChannelService {

    @GET("getChannel")
    Observable<Channel> getChannels(@Query("pId") int pId, @Query("key") String key);
}
