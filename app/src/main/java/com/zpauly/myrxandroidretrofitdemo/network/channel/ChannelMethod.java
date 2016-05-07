package com.zpauly.myrxandroidretrofitdemo.network.channel;

import com.zpauly.myrxandroidretrofitdemo.Contants;
import com.zpauly.myrxandroidretrofitdemo.entity.Channel;
import com.zpauly.myrxandroidretrofitdemo.network.RetrofitUtils;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-5-5.
 */
public class ChannelMethod {
    private Retrofit retrofit;

    private ChannelService service;

    private ChannelMethod() {
        retrofit = RetrofitUtils.initRetrofit(Contants.channel_base_url);

        service = retrofit.create(ChannelService.class);
    }

    private static ChannelMethod instance = null;

    public static ChannelMethod getInstance() {
        if (instance == null) {
            synchronized (ChannelMethod.class) {
                if (instance == null) {
                    instance = new ChannelMethod();
                }
            }
        }
        return instance;
    }

    public void getChannels(Observer<Channel> channelObserver, int pId) {
        service.getChannels(pId, Contants.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(channelObserver);
    }
}
