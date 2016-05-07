package com.zpauly.myrxandroidretrofitdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zpauly.myrxandroidretrofitdemo.R;
import com.zpauly.myrxandroidretrofitdemo.adapter.MainRecyclerViewAdapter;
import com.zpauly.myrxandroidretrofitdemo.entity.Channel;
import com.zpauly.myrxandroidretrofitdemo.network.channel.ChannelMethod;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by root on 16-5-6.
 */
public class MainFragment extends Fragment {
    private View mView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    private MainRecyclerViewAdapter adapter;

    private ChannelMethod channel;

    private Subscriber<Channel> subscriber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
        channel = ChannelMethod.getInstance();

        initView();

        return mView;
    }

    private void initView() {
        mSwipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        adapter = new MainRecyclerViewAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        mSwipeRefreshLayout.setRefreshing(true);
        loadData();
    }

    private void loadData() {
        subscriber = new Subscriber<Channel>() {
            @Override
            public void onStart() {
                super.onStart();
                mSwipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onCompleted() {
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), "Fail TO GET DATA", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Channel channel) {
                if (channel.getError_code() == 0) {
                    adapter.addAllData(channel.getResult());
                }
            }
        };

        channel.getChannels(subscriber, this.getArguments().getInt("ID"));
    }

    @Override
    public void onPause() {
        super.onPause();
        unsubscribe();
    }

    @Override
    public void onStop() {
        super.onStop();
        unsubscribe();
    }

    private void unsubscribe() {
        if (subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
